import java.util.Scanner;

public class RuleBasedChatbot {
    static String reply(String message) {
        String text = message.toLowerCase().trim();

        if (text.contains("hello") || text.contains("hi") || text.contains("hey"))
            return "Hello! How can I help you?";
        if (text.contains("your name") || text.contains("who are you"))
            return "I am a simple rule-based chatbot.";
        if (text.contains("how are you"))
            return "I am fine. Thank you for asking!";
        if (text.contains("college") || text.contains("study"))
            return "Keep learning and practicing. You can do it!";
        if (text.contains("thank"))
            return "You are welcome!";
        if (text.contains("bye") || text.contains("goodbye"))
            return "Goodbye! Have a nice day!";

        return "Sorry, I did not understand. Please try another sentence.";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Rule-Based Chatbot");
        System.out.println("Type 'bye' to end the chat.");

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();
            String answer = reply(input);
            System.out.println("Bot: " + answer);

            String lower = input.toLowerCase().trim();
            if (lower.equals("bye") || lower.equals("goodbye")) break;
        }
        sc.close();
    }
}
