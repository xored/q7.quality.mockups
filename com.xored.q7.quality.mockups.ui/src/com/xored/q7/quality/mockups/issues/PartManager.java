package com.xored.q7.quality.mockups.issues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.xored.q7.quality.mockups.issues.internal.QualityUIPlugin;

public class PartManager {
	private List<MockupPart> allParts = null;
	private List<MockupPart> roots = null;

	public PartManager() {
	}

	public static class MockupPart {
		private String name;
		private String issue;
		private String category;
		private IQ7MockupPart part;
		private List<MockupPart> children;

		public String getName() {
			return name;
		}

		public String getIssue() {
			return issue;
		}

		public IQ7MockupPart getPart() {
			return part;
		}

		public String getCategory() {
			return category;
		}

		void addChild(MockupPart part) {
			if (children == null) {
				children = new ArrayList<PartManager.MockupPart>();
			}
			children.add(part);
		}

		public List<MockupPart> getChildren() {
			return children;
		}
	}

	private synchronized void initialize() {
		if (allParts != null) {
			return;
		}
		allParts = new ArrayList<MockupPart>();

		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						QualityUIPlugin.PLUGIN_ID + ".part");
		for (IConfigurationElement cfg : elements) {
			try {
				MockupPart part = new MockupPart();
				part.part = (IQ7MockupPart) cfg
						.createExecutableExtension("class");
				part.name = cfg.getAttribute("name");
				part.category = cfg.getAttribute("category");
				part.issue = cfg.getAttribute("issue");
				allParts.add(part);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		// Move to categories
		roots = new ArrayList<PartManager.MockupPart>();
		Map<String, MockupPart> partsMap = new HashMap<String, PartManager.MockupPart>();
		MockupPart other = new MockupPart();
		for (MockupPart part : allParts) {
			String category = part.getCategory();
			if (category == null) {
				other.addChild(part);
				continue;
			}
			String[] split = category.split("\\.");
			MockupPart parentPart = getCategory(roots, split, partsMap);
			parentPart.addChild(part);
		}
		if (other.getChildren() != null && other.getChildren().size() > 0) {
			roots.add(other);
		}
	}

	private MockupPart getCategory(List<MockupPart> roots, String[] split,
			Map<String, MockupPart> categoryMap) {
		if (roots == null) {
			return null;
		}
		if (split.length == 0) {
			return null;
		}
		String ss = "";
		MockupPart prev = null;
		for (String s : split) {
			MockupPart part = categoryMap.get(ss + s);
			if (part == null) {
				part = new MockupPart();
				part.name = s;
				part.category = ss + s;
				categoryMap.put(part.category, part);
				if (prev != null) {
					prev.addChild(part);
				} else {
					roots.add(part);
				}
			}
			ss = ss + s + ".";
			prev = part;
		}
		return prev;
	}

	public List<MockupPart> getParts() {
		initialize();
		return roots;
	}
}
