package util;

import java.util.List;

public record Workflow(String name, List<Condition> conditions, String targetWorkflow) {}
