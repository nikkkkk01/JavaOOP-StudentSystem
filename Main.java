package myPackage;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class Main {
    public static void main(String[] args) {
        
        Student stud = new Student();

       
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel label1 = new JLabel("Name: ");
        JTextField nameTextfield = new JTextField();

        JLabel label2 = new JLabel("Student ID (10 digits): ");
        JTextField studentIDTextfield = new JTextField();

     
        ((AbstractDocument) studentIDTextfield.getDocument())
                .setDocumentFilter(new DigitLimitDocumentFilter(10));

        JLabel label3 = new JLabel("Course: ");
        JTextField courseCodeTextfield = new JTextField();

        inputPanel.add(label1);
        inputPanel.add(nameTextfield);
        inputPanel.add(label2);
        inputPanel.add(studentIDTextfield);
        inputPanel.add(label3);
        inputPanel.add(courseCodeTextfield);

      
        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Student Info",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
          
            stud.setName(nameTextfield.getText().trim());

            String idInput = studentIDTextfield.getText().trim();
            
            if (!idInput.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Student ID must be exactly 10 numeric digits.");
                return; 
            }
          
            stud.setStudentID(idInput);

            stud.setCourse(courseCodeTextfield.getText().trim());

           
            System.out.println("=== Student Information ===");
            System.out.println("Name: " + stud.getName());
            System.out.println("StudentID: " + stud.getStudentID());
            System.out.println("Course: " + stud.getCourse());

          
            JFrame outputFrame = new JFrame("Student Information");
            outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            outputFrame.setSize(300, 200);
            outputFrame.setLocationRelativeTo(null);

            JPanel outputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

            outputPanel.add(new JLabel("Name: "));
            outputPanel.add(new JLabel(stud.getName()));
            outputPanel.add(new JLabel("StudentID: "));
            outputPanel.add(new JLabel(stud.getStudentID()));
            outputPanel.add(new JLabel("Course: "));
            outputPanel.add(new JLabel(stud.getCourse()));

            outputFrame.add(outputPanel);
            outputFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Input cancelled.");
            
        }
    }

   
    static class DigitLimitDocumentFilter extends DocumentFilter {
        private final int maxLen;

        DigitLimitDocumentFilter(int maxLen) {
            this.maxLen = maxLen;
        }

        private String digitsOnly(String s) {
            return s == null ? "" : s.replaceAll("\\D", "");
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string == null) return;
            String digits = digitsOnly(string);
            int allowed = maxLen - fb.getDocument().getLength();
            if (allowed <= 0) return;
            if (digits.length() > allowed) digits = digits.substring(0, allowed);
            super.insertString(fb, offset, digits, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text == null) {
                super.replace(fb, offset, length, null, attrs);
                return;
            }
            String digits = digitsOnly(text);
            int allowed = maxLen - (fb.getDocument().getLength() - length);
            if (allowed <= 0) {
              
                super.replace(fb, offset, length, "", attrs);
                return;
            }
            if (digits.length() > allowed) digits = digits.substring(0, allowed);
            super.replace(fb, offset, length, digits, attrs);
        }
    }
}
