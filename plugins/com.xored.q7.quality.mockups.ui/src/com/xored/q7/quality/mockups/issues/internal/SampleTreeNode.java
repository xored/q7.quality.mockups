package com.xored.q7.quality.mockups.issues.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleTreeNode {
	public SampleTreeNode(String column1, String column2,
			SampleTreeNode... children) {
		this.column1 = column1;
		this.column2 = column2;
		this.children = new ArrayList<SampleTreeNode>(Arrays.asList(children));
		for (SampleTreeNode child : children) {
			child.parent = this;
		}
	}

	public SampleTreeNode(String column1, String column2) {
		this(column1, column2, new SampleTreeNode[0]);
	}

	public final List<SampleTreeNode> children;
	public final String column1;
	public String column2;
	public SampleTreeNode parent;

	public static List<SampleTreeNode> createSample() {
		return new ArrayList<SampleTreeNode>(Arrays.asList(new SampleTreeNode(
				"root1", "42", new SampleTreeNode("child1", "12",
						new SampleTreeNode[0]), new SampleTreeNode("child2",
						"23", new SampleTreeNode[0])),

		new SampleTreeNode("root2", "54", new SampleTreeNode("child5", "34",
				new SampleTreeNode("child6", "45", new SampleTreeNode[0])),
				new SampleTreeNode("child7", "56", new SampleTreeNode[0])),

		new SampleTreeNode("root3", "43", new SampleTreeNode("child1", "32",
				new SampleTreeNode[0]), new SampleTreeNode("child2", "323",
				new SampleTreeNode[0]))));
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
