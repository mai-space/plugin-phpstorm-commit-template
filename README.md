# Git commit template Plugin for IntelliJ
Forked from: [[MobileTribe/commit-template-idea-plugin]](https://github.com/MobileTribe/commit-template-idea-plugin).
I used parts of his code, but heavily modified it and am planning on changing more.

### Find downloadable Plugin: [here](https://github.com/Inf166/commit-template-idea-plugin-typo3/blob/master/build/idea-sandbox/plugins/commit-template-idea-plugin-typo3/lib/)

This plugin allows to create a commit message with the following template:

```html
[<FLAGS>][<TYPE>] <SUBJECT>

Breaking Changes: (optional) 
<BREAKING CHANGES HERE>    
    
Tasks: (optional) 
<TASK LIST HERE&>

To-Do's: (optional) 
<TO-DOs HERE>

refs: #<TICKET NUMBER HERE>
```

From the commit guidelines of TYPO3 [here](https://docs.typo3.org/m/typo3/guide-contributionworkflow/main/en-us/Appendix/CommitMessage.html).

## Installation

Open your preferences.<br>
<img src="src/main/resources/images/preferences.png" alt="preferences" width="550"><br>

Go to plugins in your preferences and select in the upper right "Install Plugin from Disk".<br>
<img src="src/main/resources/images/install_plugin.png" alt="install_plugin" width="550"><br>

Go to your downloaded JAR File and select it in the file-browser.<br>
<img src="src/main/resources/images/select_plugin.png" alt="select_plugin" width="550"><br>

Click apply/ok.<br>
<img src="src/main/resources/images/apply.png" alt="apply" width="550"><br>
## Usage

Open your Commit-Dialog by clicking the module or pressing `Control/CMD + K`.<br>
You can open the template dialog by pressing `Control + Shift + K`.<br>
<img src="src/main/resources/images/open_dialog.png" alt="open_dialog" width="550"><br>

Once you are in the dialog, you can navigate by using the `Tab` Key.<br>
Fill the form as you see fit.<br>
<img src="src/main/resources/images/dialog.png" alt="dialog" width="550"><br>

If you are happy with your commit message, press `Apply` and the message will be templated 
and inserted into your commit message dialog<br>
<img src="src/main/resources/images/filled_dialog.png" alt="filled_dialog" width="550"><br>

Please note, that the template is dynamic. So filling breaking changes will result in the `[!!!]` flag being set.<br>
This also works with the to-do list which results in the `[WIP]` flag being set.<br>
If you do not want any flags, just fill the `Done Tasks` Section, so only the Type will be displayed.<br>
<img src="src/main/resources/images/inserted_dialog.png" alt="inserted_dialog" width="550"><br>

## Open Tasks:
- [ ] Reorder the lists in Commit Message
- [ ] Fix Keyboard Shortcut
- [ ] Automatically commit and push selected changes
- [ ] Enhance UX by fixing Design and use different elements in Swing

## Partner
<a href="https://www.iwmedien.de/">
    <img src="src/main/resources/images/iwmedien.png" alt="IW Medien Logo" width="3282">
</a>
    
