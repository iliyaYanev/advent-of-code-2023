package day_19;

import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import util.Condition;
import util.Regex;
import util.Workflow;

public class Aplenty {

    public static long acceptedPartsSum(String input) {
        long acceptedSum = 0;

        List<Map<Character, Integer>> acceptedParts = new ArrayList<>();
        String[] parts = input.trim().split(System.lineSeparator() + System.lineSeparator());

        Map<String, Workflow> workflows = parseWorkflows(parts[0]);
        List<Map<Character, Integer>> partsList = parseParts(parts[1]);

        for (Map<Character, Integer> part: partsList) {
            Workflow workflow = workflows.get("in");

            nextWorkflow:
            while (true) {
                for (Condition condition: workflow.conditions()) {
                    Integer registerValue = part.get(condition.register());

                    switch (condition.operation()) {
                        case '<' -> {
                            if (registerValue < condition.value()) {
                                if (!condition.targetWorkflow().equals("A")) {
                                    if (condition.targetWorkflow().equals("R")) {
                                        break nextWorkflow;
                                    }

                                    workflow = workflows.get(condition.targetWorkflow());
                                    continue nextWorkflow;
                                } else {
                                    acceptedParts.add(part);
                                    break nextWorkflow;
                                }

                            }
                        }
                        case '>' -> {
                            if (registerValue > condition.value()) {
                                if (condition.targetWorkflow().equals("A")) {
                                    acceptedParts.add(part);
                                    break nextWorkflow;
                                }

                                if (condition.targetWorkflow().equals("R")) {
                                    break nextWorkflow;
                                }

                                workflow = workflows.get(condition.targetWorkflow());
                                continue nextWorkflow;
                            }
                        }
                    }
                }

                if (workflow.targetWorkflow().equals("A")) {
                    acceptedParts.add(part);
                    break;
                }

                if (workflow.targetWorkflow().equals("R")) {
                    break;
                }

                workflow = workflows.get(workflow.targetWorkflow());
            }
        }

        for (Map<Character, Integer> part: acceptedParts) {
            for (Map.Entry<Character, Integer> es: part.entrySet()) {
                acceptedSum += es.getValue();
            }
        }

        return acceptedSum;
    }

    public static long distinctRatingCombinations(String input) {
        long ratingCombinations = 0;
        boolean hasUnprocessedRanges = true;

        String[] parts = input.trim().split(System.lineSeparator() + System.lineSeparator());

        List<Map<Character, Range<Integer>>> acceptedRanges = new ArrayList<>();
        Map<String, ArrayList<HashMap<Character, Range<Integer>>>> workflowQueue = new HashMap<>();
        Map<String, Workflow> workflows = parseWorkflows(parts[0]);

        for (Workflow workflow: workflows.values()) {
            workflowQueue.put(workflow.name(), new ArrayList<>());
        }

        List<HashMap<Character,Range<Integer>>> partRanges = new ArrayList<>() {{
            add(new HashMap<>() {{
                put('x', Range.closed(1, 4000));
                put('m', Range.closed(1, 4000));
                put('a', Range.closed(1, 4000));
                put('s', Range.closed(1, 4000));
            }});
        }};

        workflowQueue.get("in").addAll(partRanges);

        while (hasUnprocessedRanges) {
            hasUnprocessedRanges = false;

            for (Map.Entry<String, ArrayList<HashMap<Character, Range<Integer>>>> work: workflowQueue.entrySet()) {
                if (work.getValue().isEmpty()) {
                    continue;
                }

                hasUnprocessedRanges = true;

                Workflow workflow = workflows.get(work.getKey());
                List<HashMap<Character, Range<Integer>>> ranges = work.getValue();
                workflowQueue.put(work.getKey(), new ArrayList<>());

                nextRange:
                for (HashMap<Character, Range<Integer>> range: ranges) {
                    for (Condition condition: workflow.conditions()) {
                        Range<Integer> registerRange = range.get(condition.register());
                        Range<Integer> conditionRange;
                        Range<Integer> remainingRange;

                        if (condition.operation() == '<') {
                            conditionRange = Range.closed(1, condition.value() - 1);
                            remainingRange = Range.closed(condition.value(), 4000);
                        } else {
                            conditionRange = Range.closed(condition.value() + 1, 4000);
                            remainingRange = Range.closed(1, condition.value());
                        }

                        if (!registerRange.isConnected(conditionRange)) {
                            continue;
                        }

                        Range<Integer> overlapping = registerRange.intersection(conditionRange);
                        HashMap<Character, Range<Integer>> newRange = new HashMap<>(range);
                        newRange.put(condition.register(), overlapping);

                        if (condition.targetWorkflow().equals("A")) {
                            acceptedRanges.add(newRange);
                        } else if (!condition.targetWorkflow().equals("R")) {
                            workflowQueue.get(condition.targetWorkflow()).add(newRange);
                        }

                        if (remainingRange.isConnected(registerRange)) {
                            range.put(condition.register(), remainingRange.intersection(registerRange));
                        } else {
                            continue nextRange;
                        }
                    }

                    if (workflow.targetWorkflow().equals("A")) {
                        acceptedRanges.add(range);
                    } else if (!workflow.targetWorkflow().equals("R")) {
                        workflowQueue.get(workflow.targetWorkflow()).add(range);
                    }
                }
            }
        }

        for (Map<Character, Range<Integer>> acceptedRange: acceptedRanges) {
            long rangeProduct = 1;

            for (Map.Entry<Character, Range<Integer>> es: acceptedRange.entrySet()) {
                Range<Integer> range = es.getValue();
                rangeProduct *= range.upperEndpoint() - range.lowerEndpoint() + 1;
            }

            ratingCombinations += rangeProduct;
        }

        return ratingCombinations;
    }

    private static Map<String, Workflow> parseWorkflows(String input) {
        Map<String, Workflow> workflows = new HashMap<>();
        String[] workflowsLines = input.split(System.lineSeparator());

        for (String workflowLine: workflowsLines) {
            LinkedList<Condition> conditions = new LinkedList<>();
            List<String> workflowHeaders = Regex.matchGroups("(\\w+)\\{(.*)\\}", workflowLine);

            assert workflowHeaders != null;
            String[] workflowParts = workflowHeaders.get(1).split(",");

            for (int i = 0; i < workflowParts.length - 1; i++) {
                List<String> cond = Regex.matchGroups("(\\w+)([^\\w])([0-9]+):(\\w+)", workflowParts[i]);

                assert cond != null;
                conditions.add(new Condition(cond.get(0).charAt(0), cond.get(1).charAt(0), Integer.parseInt(cond.get(2)), cond.get(3)));
            }

            workflows.put(
                workflowHeaders.get(0),
                new Workflow(workflowHeaders.get(0), conditions, workflowParts[workflowParts.length - 1])
            );
        }

        return workflows;
    }

    private static List<Map<Character, Integer>> parseParts(String input) {
        List<Map<Character, Integer>> parts = new ArrayList<>();
        String[] partsLines = input.split(System.lineSeparator());

        for (String partLine: partsLines) {
            String[] partRegisters = partLine.substring(1, partLine.length() - 1).split(",");
            Map<Character, Integer> part = new HashMap<>();

            for (String partRegister: partRegisters) {
                String[] registerParts = partRegister.split("=");
                part.put(registerParts[0].charAt(0), Integer.parseInt(registerParts[1]));
            }

            parts.add(part);
        }

        return parts;
    }
}
