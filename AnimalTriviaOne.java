package AnimalTriviaOne;

import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Collections;

public class AnimalTriviaOne extends JFrame {
  private JLabel questionLabel;

  private JButton answer1Button;

  private JButton answer2Button;

  private JButton answer3Button;

  private JButton answer4Button;

  private JPanel panel;

  private List<Question> questions;

  private int currentQuestion;

  private int score;
  
  

  public AnimalTriviaOne() {
    super("Animal Trivia One");
    
 


    setSize(1920, 1080);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setResizable(true);

    questionLabel = new JLabel();
    

    answer1Button = new JButton();

    answer2Button = new JButton();

    answer3Button = new JButton();

    answer4Button = new JButton();

    panel = new JPanel();

    panel.setLayout(new GridLayout(5, 2));

    panel.add(questionLabel);

    panel.add(answer1Button);

    panel.add(answer2Button);

    panel.add(answer3Button);

    panel.add(answer4Button);

    add(panel);

    questions = new ArrayList<Question>();

   

    score = 0;

    try {
    	
      BufferedReader reader = new BufferedReader(
          new FileReader("C:\\Users\\Nugge\\Downloads\\School Java work\\animals.txt"));
      
      

      String line;

      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(";");

        String question = parts[0];

        String answer1 = parts[1];

        String answer2 = parts[2];

        String answer3 = parts[3];

        String answer4 = parts[4];

        int correctAnswer = Integer.parseInt(parts[5]);

        questions.add(new Question(
            question, answer1, answer2, answer3, answer4, correctAnswer));
        
        Collections.shuffle(questions, new Random());
        currentQuestion = 0;
      }

      reader.close();
      
      

    } catch (Exception e) {
      e.printStackTrace();
    }

    answer1Button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        checkAnswer(1);
      }
    });

    answer2Button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        checkAnswer(2);
      }
    });

    answer3Button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        checkAnswer(3);
      }
    });

    answer4Button.addActionListener(new ActionListener()

        {
          public void actionPerformed(ActionEvent e) {
            checkAnswer(4);
          }
        });

    nextQuestion();

    setVisible(true);
  }

  private void checkAnswer(int answer) {
    if (questions.get(currentQuestion).isCorrect(answer)) {
      JOptionPane.showMessageDialog(this, "The answer is correct! +1 Point!");

      score++;

    } else {
      JOptionPane.showMessageDialog(this, "Answer is incorrect :C +0 Points!");
    }

    currentQuestion++;

    if (currentQuestion >= questions.size()) {
      JOptionPane.showMessageDialog(this, "Your score is: " + score);

      System.exit(0);

    } else {
      nextQuestion();
    }
  }

  private void nextQuestion() {
    Question question = questions.get(currentQuestion);
    questionLabel.setHorizontalAlignment(JLabel.CENTER);

    questionLabel.setText(question.getQuestion());

    answer1Button.setText(question.getAnswer1());

    answer2Button.setText(question.getAnswer2());

    answer3Button.setText(question.getAnswer3());

    answer4Button.setText(question.getAnswer4());
  }

  // Main

  public static void main(String[] args) {
    new AnimalTriviaOne();
  }

  private class Question {
    private String question;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private int correctAnswer;

    public Question(String question, String answer1, String answer2,
        String answer3, String answer4, int correctAnswer) {
      this.question = question;

      this.answer1 = answer1;

      this.answer2 = answer2;

      this.answer3 = answer3;

      this.answer4 = answer4;

      this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
      return question;
    }

    public String getAnswer1() {
      return answer1;
    }

    public String getAnswer2() {
      return answer2;
    }

    public String getAnswer3() {
      return answer3;
    }

    public String getAnswer4() {
      return answer4;
    }

    public boolean isCorrect(int answer) {
      return answer == correctAnswer;
    }
  }
}