import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * MorseCodeTranslatorGUI.java
 * 
 * This program provides a graphical user interface (GUI) for translating between English and Morse code.
 * It uses a binary tree structure to store and retrieve Morse code mappings for characters.
 * 
 * The application allows users to:
 * - Translate an input English text into Morse code.
 * - Translate an input Morse code into English text.
 * 
 * Design:
 * - The Morse code is stored in a binary tree where '.' moves to the left node, and '-' moves to the right node.
 * - Each node in the tree corresponds to a character or symbol in English.
 * - For reverse translation, the tree is traversed by following the Morse code symbols.
 * 
 * 
 * GUI:
 * - The graphical user interface is built using Java's Swing library.
 * - Users can input English text and get the Morse code equivalent or input Morse code and get the English text.
 * - The GUI includes buttons for translation, clearing the input, and exiting the program.
 * 
 * 
 * Author: Niloufar Zafari
 * 
 */

// Node class for Binary Tree
class MorseNode {
    char letter;  // The letter represented by this node
    MorseNode dot;  // Left child (for dots)
    MorseNode dash; // Right child (for dashes)

    public MorseNode(char letter) {
        this.letter = letter;
        this.dot = null;
        this.dash = null;
    }
}

// Main class for Morse code translation with GUI
public class MorseCode {
    private MorseNode root;
    private Map<Character, String> morseMap;

    // Constructor to initialize the Morse code tree
    public MorseCode() {
        root = new MorseNode(' '); // Root node is empty
        morseMap = new HashMap<>();
        buildMorseTree();
        buildMorseMap();
    }

    // Build the Morse code tree
    private void buildMorseTree() {
        insertMorseCode('A', ".-");
        insertMorseCode('B', "-...");
        insertMorseCode('C', "-.-.");
        insertMorseCode('D', "-..");
        insertMorseCode('E', ".");
        insertMorseCode('F', "..-.");
        insertMorseCode('G', "--.");
        insertMorseCode('H', "....");
        insertMorseCode('I', "..");
        insertMorseCode('J', ".---");
        insertMorseCode('K', "-.-");
        insertMorseCode('L', ".-..");
        insertMorseCode('M', "--");
        insertMorseCode('N', "-.");
        insertMorseCode('O', "---");
        insertMorseCode('P', ".--.");
        insertMorseCode('Q', "--.-");
        insertMorseCode('R', ".-.");
        insertMorseCode('S', "...");
        insertMorseCode('T', "-");
        insertMorseCode('U', "..-");
        insertMorseCode('V', "...-");
        insertMorseCode('W', ".--");
        insertMorseCode('X', "-..-");
        insertMorseCode('Y', "-.--");
        insertMorseCode('Z', "--..");
    }

    // Insert a letter into the Morse tree based on its code
    private void insertMorseCode(char letter, String morse) {
        MorseNode currentNode = root;
        for (char signal : morse.toCharArray()) {
            if (signal == '.') {
                if (currentNode.dot == null) {
                    currentNode.dot = new MorseNode(' ');
                }
                currentNode = currentNode.dot;
            } else if (signal == '-') {
                if (currentNode.dash == null) {
                    currentNode.dash = new MorseNode(' ');
                }
                currentNode = currentNode.dash;
            }
        }
        currentNode.letter = letter; // Assign the letter at the final node
    }

    // Build a HashMap for English to Morse code translation
    private void buildMorseMap() {
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");
    }

    // Translate English to Morse Code
    public String englishToMorse(String input) {
        StringBuilder morseCode = new StringBuilder();
        input = input.toUpperCase();
        for (char letter : input.toCharArray()) {
            if (morseMap.containsKey(letter)) {
                morseCode.append(morseMap.get(letter)).append(" ");
            } else if (letter == ' ') {
                morseCode.append(" / ");  // Separate words with a '/'
            }
        }
        return morseCode.toString();
    }

    // Translate Morse Code to English using the tree
    public String morseToEnglish(String morse) {
        StringBuilder english = new StringBuilder();
        String[] morseLetters = morse.trim().split(" ");
        for (String morseLetter : morseLetters) {
            if (morseLetter.equals("/")) {
                english.append(" "); // Separate words
            } else {
                english.append(morseLetterToChar(morseLetter));
            }
        }
        return english.toString();
    }

    // Find a letter from the Morse code using the tree
    private char morseLetterToChar(String morseLetter) {
        MorseNode currentNode = root;
        for (char signal : morseLetter.toCharArray()) {
            if (signal == '.') {
                if (currentNode.dot != null) {
                    currentNode = currentNode.dot;
                }
            } else if (signal == '-') {
                if (currentNode.dash != null) {
                    currentNode = currentNode.dash;
                }
            }
        }
        return currentNode.letter;
    }

    // Create the GUI for the Morse code translator
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Morse Code Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Layout
        JPanel panel = new JPanel(new GridLayout(5, 1));
        
        // Labels and text fields
        JLabel englishLabel = new JLabel("English:");
        JTextField englishField = new JTextField();
        JLabel morseLabel = new JLabel("Morse Code:");
        JTextField morseField = new JTextField();
        JTextArea resultArea = new JTextArea(5, 20);
        
        // Buttons
        JButton englishToMorseButton = new JButton("Translate English to Morse");
        JButton morseToEnglishButton = new JButton("Translate Morse to English");
        
        // Action listeners
        englishToMorseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String englishText = englishField.getText();
                String morseCode = englishToMorse(englishText);
                resultArea.setText("Morse Code: " + morseCode);
            }
        });

        morseToEnglishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String morseText = morseField.getText();
                String englishTranslation = morseToEnglish(morseText);
                resultArea.setText("English Translation: " + englishTranslation);
            }
        });

        // Adding components to the panel
        panel.add(englishLabel);
        panel.add(englishField);
        panel.add(morseLabel);
        panel.add(morseField);
        panel.add(englishToMorseButton);
        panel.add(morseToEnglishButton);
        panel.add(resultArea);
        
        // Add panel to frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        // Display the window
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Initialize Morse code translator
        MorseCode translator = new MorseCode();

        // Schedule a job for the event-dispatching thread:
        // Creating and showing the GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                translator.createAndShowGUI();
            }
        });
    }
}
