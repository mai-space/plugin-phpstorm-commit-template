package com.iwmedien.committemplateideaplugintypo3;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @inspired-by Damien Arrachequesne
 * @author Joel Maximilian Mai
 */
class CommitMessage {
    public static final Pattern COMMIT_FIRST_LINE_FORMAT = Pattern.compile("[^([a-z]+)(\\((.+)\\))?] (.+)");
    public static final Pattern COMMIT_CLOSES_FORMAT = Pattern.compile("refs: (.+)");

    private ChangeType changeType;
    private String shortDescription, longDescription, breakingChanges, workInProgress, closedIssues;

    private CommitMessage() {
        this.longDescription = "";
        this.breakingChanges = "";
        this.workInProgress = "";
        this.closedIssues = "";
    }

    public CommitMessage(ChangeType changeType, String shortDescription, String longDescription,
                         String breakingChanges, String workInProgress, String closedIssues) {
        this.changeType = changeType;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.breakingChanges = breakingChanges;
        this.workInProgress = workInProgress;
        this.closedIssues = closedIssues;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (isNotBlank(breakingChanges)) {
            builder.append("[!!!]");
        }
        if (isNotBlank(workInProgress)) {
            builder.append("[WIP]");
        }
        builder.append(changeType.label());
        builder
                .append(" ")
                .append(shortDescription);

        if (isNotBlank(longDescription)) {
            String tasks = "Tasks: \n" + longDescription;
            builder
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append(tasks);
        }

        if (isNotBlank(breakingChanges)) {
            String breaking = "Breaking Changes: \n" + breakingChanges;
            builder
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append(breaking);
        }

        if (isNotBlank(workInProgress)) {
            String wip = "To-Do's: \n" + workInProgress;
            builder
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append(wip);
        }

        if (isNotBlank(closedIssues)) {
            builder.append(System.lineSeparator());
            for (String closedIssue : closedIssues.split(",")) {
                builder
                        .append(System.lineSeparator())
                        .append("refs: ")
                        .append(formatClosedIssue(closedIssue));
            }
        }

        return builder.toString();
    }

    private String formatClosedIssue(String closedIssue) {
        String trimmed = closedIssue.trim();
        return (StringUtils.isNumeric(trimmed) ? "#" : "") + trimmed;
    }

    public static CommitMessage parse(String message) {
        CommitMessage commitMessage = new CommitMessage();

        try {
            Matcher matcher = COMMIT_FIRST_LINE_FORMAT.matcher(message);
            if (!matcher.find()) return commitMessage;

            commitMessage.changeType = ChangeType.valueOf(matcher.group(1).toUpperCase());
            commitMessage.shortDescription = matcher.group(3);

            String[] strings = message.split("\n");
            if (strings.length < 2) return commitMessage;

            int pos = 1;
            StringBuilder stringBuilder;

            stringBuilder = new StringBuilder();
            for (; pos < strings.length; pos++) {
                String lineString = strings[pos];
                if (lineString.startsWith("Breaking Changes:") || lineString.startsWith("To-Do's:") || lineString.startsWith("refs:")) break;
                stringBuilder.append(lineString).append('\n');
            }
            commitMessage.longDescription = stringBuilder.toString().trim();

            stringBuilder = new StringBuilder();
            for (; pos < strings.length; pos++) {
                String lineString = strings[pos];
                if (lineString.startsWith("refs:")) break;
                stringBuilder.append(lineString).append('\n');
            }
            commitMessage.breakingChanges = stringBuilder.toString().trim().replace("Breaking Changes: ", "");

            matcher = COMMIT_CLOSES_FORMAT.matcher(message);
            stringBuilder = new StringBuilder();
            while (matcher.find()) {
                stringBuilder.append(matcher.group(1)).append(',');
            }
            if (stringBuilder.length() > 0) stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            commitMessage.closedIssues = stringBuilder.toString();

        } catch (RuntimeException e) {}

        return commitMessage;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getWorkInProgress() {
        return workInProgress;
    }

    public String getBreakingChanges() {
        return breakingChanges;
    }

    public String getClosedIssues() {
        return closedIssues;
    }

}