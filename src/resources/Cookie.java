import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Cookie extends JFrame {
    private JTextField counterField;
    public Cookie() {
        super ("Cookie Clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(638, 803));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents () {
        SpringLayout springLayout = new SpringLayout();
        JPanel JPanel = new JPanel();
        JPanel.setLayout(springLayout);

        // Banner

        JLabel bannerImg = loadImage("banner.png", true, 450, 100);

        JPanel.add(bannerImg);
        springLayout.putConstraint(SpringLayout.WEST, bannerImg, 90, SpringLayout.WEST, JPanel);
        springLayout.putConstraint(SpringLayout.NORTH, bannerImg, 28, SpringLayout.NORTH, JPanel);

        // Cookie

        JButton cookieButton = createImageButton("cookie.png");
        cookieButton.addActionListener((ActionEvent e) -> {
            int counter = Integer.parseInt(counterField.getText());
            counterField.setText(Integer.toString(++counter));
        });
        
        JPanel.add(cookieButton);
        springLayout.putConstraint(SpringLayout.WEST, cookieButton, 175, SpringLayout.WEST, JPanel);
        springLayout.putConstraint(SpringLayout.NORTH, cookieButton, 225, SpringLayout.NORTH, JPanel);

        // counter label

        JLabel counterLabel = new JLabel("Clicks: ");
        counterLabel.setFont(new Font("Dialog", Font.BOLD, 26));

        JPanel.add(counterLabel);
        springLayout.putConstraint(SpringLayout.WEST, counterLabel, 95, SpringLayout.WEST, JPanel);
        springLayout.putConstraint(SpringLayout.NORTH, counterLabel, 550, SpringLayout.NORTH, JPanel);

        // counter field

        counterField = new JTextField(10);
        counterField.setFont(new Font("Dialog", Font.BOLD, 26));
        counterField.setHorizontalAlignment(SwingConstants.RIGHT);
        counterField.setText("0");
        counterField.setEditable(false);
        
        JPanel.add(counterField);
        springLayout.putConstraint(SpringLayout.WEST, counterField, 210, SpringLayout.WEST, JPanel);
        springLayout.putConstraint(SpringLayout.NORTH, counterField, 550, SpringLayout.NORTH, JPanel);
        
        // reset button

        JButton resetButton = new JButton("Resetar");
        resetButton.setFont(new Font("Dialog",Font.BOLD, 18));
        resetButton.addActionListener((ActionEvent e) -> {
            counterField.setText("0");
        });

        JPanel.add(resetButton);
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 250, SpringLayout.WEST, JPanel);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 700, SpringLayout.NORTH, JPanel);
        
        this.getContentPane().add(JPanel);

    }

    private JButton createImageButton(String fileName){
        JButton button;
        try{
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            Image image = ImageIO.read(inputStream);
            button = new JButton(new ImageIcon(image));
            return button;
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    private JLabel loadImage (String fileName, boolean  isResized, int targetWidth, int targetHeight){

        BufferedImage image;
        JLabel imageContainer;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            image = ImageIO.read(inputStream);
            if(isResized){
                image = resizeImage(image, targetWidth, targetHeight);
            }
            imageContainer = new JLabel(new ImageIcon(image));
            return imageContainer;
        } catch (Exception e) {
            System.out.println("Error" + e);
            return null;
        }
    }

    private BufferedImage resizeImage (BufferedImage image, int targetWidth, int targetHeight){
        BufferedImage newImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return newImage;
    }

    }
