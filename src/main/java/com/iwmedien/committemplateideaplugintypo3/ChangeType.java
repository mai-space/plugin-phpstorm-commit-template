package com.iwmedien.committemplateideaplugintypo3;

/**
 * From https://docs.typo3.org/m/typo3/guide-contributionworkflow/main/en-us/Appendix/CommitMessage.html
 *
 * @inspired-by Damien Arrachequesne
 * @author Joel Maximilian Mai
 */
public enum ChangeType {

    FEATURE("Features", "A new feature (also small additions)"),
    DOCS("Documentations", "This tag is used for changes regarding the documentation"),
    BUGFIX("Bugfixes", "A fix for a bug"),
    SECURITY("Security", "Visualizes that a change fixes a security issue. This tag is used by the Security Team."),
    TASK("Tasks", "Anything not covered by the above categories. E.g. Refactoring of a component");

    public final String title;
    public final String description;

    ChangeType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String label() {
        return String.format("[%s]", this.name());
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s", this.label(), this.description);
    }
}
