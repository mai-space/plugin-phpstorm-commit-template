package com.iwmedien.committemplateideaplugintypo3;

import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.border.Border;
import java.util.Enumeration;

/**
 * @inspired-by Damien Arrachequesne
 * @author Joel Maximilian Mai
 */
public class CommitPanel {
    private JPanel mainPanel;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JTextArea breakingChanges;
    private JTextArea workInProgress;
    private JTextField closedIssues;
    private JRadioButton featureRadioButton;
    private JRadioButton bugfixRadioButton;
    private JRadioButton docsRadioButton;
    private JRadioButton securityRadioButton;
    private JRadioButton taskRadioButton;
    private ButtonGroup changeTypeGroup;

    CommitPanel(CommitMessage commitMessage) {

        Color lightGrey = new Color(111,111,111,255);
        Border border = BorderFactory.createLineBorder(lightGrey);
        longDescription.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        breakingChanges.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        workInProgress.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));


        longDescription.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getModifiersEx() > 0) {
                        longDescription.transferFocusBackward();
                    } else {
                        longDescription.transferFocus();
                    }
                    e.consume();
                }
            }
        });
        breakingChanges.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getModifiersEx() > 0) {
                        breakingChanges.transferFocusBackward();
                    } else {
                        breakingChanges.transferFocus();
                    }
                    e.consume();
                }
            }
        });
        workInProgress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getModifiersEx() > 0) {
                        workInProgress.transferFocusBackward();
                    } else {
                        workInProgress.transferFocus();
                    }
                    e.consume();
                }
            }
        });


        if (commitMessage != null) {
            restoreValuesFromParsedCommitMessage(commitMessage);
        }
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    CommitMessage getCommitMessage() {
        return new CommitMessage(
                getSelectedChangeType(),
                shortDescription.getText().trim(),
                longDescription.getText().trim(),
                breakingChanges.getText().trim(),
                workInProgress.getText().trim(),
                closedIssues.getText().trim()
        );
    }

    private ChangeType getSelectedChangeType() {
        for (Enumeration<AbstractButton> buttons = changeTypeGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return ChangeType.valueOf(button.getActionCommand().toUpperCase());
            }
        }
        return null;
    }

    private void restoreValuesFromParsedCommitMessage(CommitMessage commitMessage) {
        if (commitMessage.getChangeType() != null) {
            for (Enumeration<AbstractButton> buttons = changeTypeGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();

                if (button.getActionCommand().equalsIgnoreCase(commitMessage.getChangeType().label())) {
                    button.setSelected(true);
                }
            }
        }
        shortDescription.setText(commitMessage.getShortDescription());
        longDescription.setText(commitMessage.getLongDescription());
        breakingChanges.setText(commitMessage.getBreakingChanges());
        workInProgress.setText(commitMessage.getWorkInProgress());
        closedIssues.setText(commitMessage.getClosedIssues());
    }
}
