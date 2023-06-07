# Gmail Testing with Selenium and Google Chrome Driver

This repository contains tests for the Gmail website using Selenium and the Google Chrome driver.

## Tests

The following test classes are included:

- `GmailBackButtonTest`
- `GmailDownloadAttachmentsTest`
- `GmailDragAndDropTest`
- `GmailInputsAndButtonsTest`
- `GmailLogOutTest`
- `GmailSignInTest`
- `GmailSignUpTest`
- `GmailUploadAttachementsEmail`

## Usage

To run the tests, you will need to have Selenium and the Google Chrome driver installed. You can then run the tests using your preferred test runner.

### Step 1:
Copy gradle project into **tests/** folder.

### Step 2:
``` 
docker compose up
docker exec -it [container name] bash
```
### Step 3:
```cd tests/gradle_project_name```

### Step 4:
```gradle test```
