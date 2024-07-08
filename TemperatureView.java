package Temperature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Temperature.command_processer.C2FCommand;
import Temperature.command_processer.Command;
import Temperature.command_processer.CommandProcessor;
import Temperature.command_processer.ExitCommand;
import Temperature.command_processer.F2CCommand;
import Temperature.observer.Subcriber;


public class TemperatureView extends JFrame implements Subcriber{
    private String title;
    private JPanel jPanelRemote;
    private JLabel jLabelInput1Remote , jLabelInput2Remote;
    private JTextField jTextFieldInput1Remote, jTextFieldInput2Remote;
    private JMenuBar menuBarRemote = null;
    private TemperatureModel temperatureModelRemote = null;
    private EnterController enterControllerRemote = null;
   private MenuController menuControllerRemote = null;
   private CommandProcessor commandProcessorRemote = null;
    private boolean bool = false;

    public TemperatureView(TemperatureModel temperatureModel , CommandProcessor commandProcessor) {
        commandProcessorRemote = commandProcessor;

        temperatureModelRemote =  temperatureModel;
        temperatureModelRemote.subcriber(this);

        enterControllerRemote = new EnterController(temperatureModel);

        menuControllerRemote = new MenuController(temperatureModel);

        builPanel();
        add(jPanelRemote);
        buildMenu();
        title = "Temperature Converter";
        setTitle(title);
        setSize(700, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void builPanel() {
        jPanelRemote = new JPanel();
        jLabelInput1Remote = new JLabel("Celsius");
        jPanelRemote.add(jLabelInput1Remote);
        jLabelInput2Remote = new JLabel("Farenheit");
        jPanelRemote.add(jLabelInput2Remote);

        jTextFieldInput1Remote = new JTextField(10);
        jTextFieldInput1Remote.addActionListener(enterControllerRemote);
        jPanelRemote.add(jTextFieldInput1Remote);
        jTextFieldInput2Remote = new JTextField(10);
        jTextFieldInput2Remote.addActionListener(enterControllerRemote);
        jPanelRemote.add(jTextFieldInput2Remote);
      
       
    }

     public void buildMenu() {
        menuBarRemote = new JMenuBar();
        setJMenuBar(menuBarRemote);
        JMenu commandJMenuRemote = new JMenu("Commands");
        JMenuItem f2cItemRemote = new JMenuItem("c2f");
        JMenuItem c2fItemRemote = new JMenuItem("f2c");
        JMenuItem exitItemRemote = new JMenuItem("exit");

        c2fItemRemote.addActionListener(menuControllerRemote);
        f2cItemRemote.addActionListener(menuControllerRemote);
        exitItemRemote.addActionListener(menuControllerRemote);


        commandJMenuRemote.add(f2cItemRemote);
        commandJMenuRemote.add(c2fItemRemote);
        commandJMenuRemote.add(exitItemRemote);

        menuBarRemote.add(commandJMenuRemote);
    }
   
  
   
   

    class EnterController implements ActionListener {
        public EnterController(TemperatureModel temperatureModel){
            temperatureModelRemote = temperatureModel;
        }
    
    
        @Override
        public void actionPerformed(ActionEvent e) {
           String number = e.getActionCommand();
        String command = "";
           if (number.equals(jTextFieldInput1Remote.getText())) {
                command = "c2f";
           } else if (number.equals(jTextFieldInput2Remote.getText())) {
            command = "f2c";
           }
          

            if (command.equals("c2f")){
                double c = Double.parseDouble(jTextFieldInput1Remote.getText());
                bool = false;
                temperatureModelRemote.c2f(c);
               
            }else if(command.equals("f2c")){
                double f = Double.parseDouble(jTextFieldInput2Remote.getText());
                bool = true;
                temperatureModelRemote.f2c(f);
           
            }else if(command.equals("exit")){
                temperatureModelRemote.exit();
            }
        }
    }


    class MenuController  implements ActionListener {
        public MenuController (TemperatureModel temperatureModel){
            temperatureModelRemote = temperatureModel;
        }
   
    
        @Override
        public void actionPerformed(ActionEvent e) {
        
            String cmd = e.getActionCommand();
        
            Command commandRemote = null;
            if(cmd.equals("c2f")){
                double c = Double.parseDouble(jTextFieldInput1Remote.getText());
                commandRemote = new C2FCommand(temperatureModelRemote, c);
               bool = false;
                commandProcessorRemote.execute(commandRemote);
                
            } else if (cmd.equals("f2c")){
                double f = Double.parseDouble(jTextFieldInput2Remote.getText());
                commandRemote = new F2CCommand(temperatureModelRemote, f);
                bool = true;
                commandProcessorRemote.execute(commandRemote);
            } else if (cmd.equals("exit")){
                commandRemote = new ExitCommand(temperatureModelRemote);
                commandProcessorRemote.execute(commandRemote);

            }
        }
    }

    @Override
    public void update() {
     
        double result = temperatureModelRemote.getResult();
        DecimalFormat df = new DecimalFormat("#.###");
        String s = df.format(result);
        if (bool) {
            jTextFieldInput1Remote.setText(s);
        } else {
            jTextFieldInput2Remote.setText(s);
        }
    }

    
}



