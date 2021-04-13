package com.game.rememberwhen;

public class Prompt {
    public String prompt;

    public Prompt() {}

    public Prompt(String prompt) {
        this.prompt = prompt;
    }
}
/*
import java.security.SecureRandom;
import java.util.HashMap;

// POJO(Plain Old Java Object) / Model Class for Room Entity
public class Prompt {
    private final int promptId; // Final Because want to keep it immutable

    // Public constructor generating random pin for roomId
    public Prompt() {
        SecureRandom randomPrompt = new SecureRandom();
        promptId = randomPrompt.nextInt(27) + 1;
    }

}

// old Firestore code for StorytellingActivity
    FirebaseFirestore fs = this.initFireStore();
    final CollectionReference collectionReference = fs.collection("/prompts");

    @Override
    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
        if (task.isSuccessful()) {
            DocumentSnapshot document = task.getResult();
            final HashMap<String, Object> promptData = (HashMap<String, Object>) document.getData();
            Prompt prompt = new Prompt();

            if (promptData.get("viewed?")) {
                // Regenerate prompt
                prompt = new Prompt();
            } else {
                promptData.set("viewed?") = true;
                int promptId = prompt.promptId;
                // display promptData.get("prompt")
            }
        }
    }
*/
