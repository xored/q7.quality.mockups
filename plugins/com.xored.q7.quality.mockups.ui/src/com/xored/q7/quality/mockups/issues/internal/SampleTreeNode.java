package com.xored.q7.quality.mockups.issues.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class SampleTreeNode {
	public final List<SampleTreeNode> children = new ArrayList<>();
	public final String column1;
	public String column2;
	public String column3;
	private Optional<SampleTreeNode> parent = Optional.empty();

	public SampleTreeNode(String column1, String column2,
			SampleTreeNode... children) {
		this.column1 = column1;
		this.column2 = column2;
		Arrays.stream(children).forEach(this::addChild);
	}

	public SampleTreeNode(String column1, String column2) {
		this(column1, column2, new SampleTreeNode[0]);
	}

	public void addChild(SampleTreeNode child) {
		children.add(child);
		assert !child.parent.isPresent();
		child.parent = Optional.of(this);
	}

	public static List<SampleTreeNode> createSample() {
		return Arrays.asList(
				new SampleTreeNode("root1", "42",
						new SampleTreeNode("child1", "12"),
						new SampleTreeNode("child2", "23")),
				new SampleTreeNode("root2", "54",
						new SampleTreeNode("child5", "34",
								new SampleTreeNode("child6", "45")),
						new SampleTreeNode("child7", "56")),
				new SampleTreeNode("root3", "43",
						new SampleTreeNode("child1", "32"),
						new SampleTreeNode("child2", "323")));
	}

	@Override
	public String toString() {
		return column1;
	}

	public Optional<SampleTreeNode> getParent() {
		return parent;
	}
}
