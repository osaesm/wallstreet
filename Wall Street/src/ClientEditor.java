import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class ClientEditor
{
  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ClientEditor window = new ClientEditor();
          window.frame.setVisible(true);
          window.start();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  private LemonadeShop LS = new LemonadeShop();
  private SmallBusiness SB = new SmallBusiness();
  private ChainStore CS = new ChainStore();
  private Stock S = new Stock();
  private HedgeFund HF = new HedgeFund();
  private double cash = 10;
  private Earnings cashPerSec = new Earnings();
  private Timer timer = new Timer();
  private DecimalFormat df = new DecimalFormat("0.00");
  private JLabel lblCash, lblCashPerDay;
  private JButton LSButton, SBButton, CSButton, SButton, HFButton;
  private BufferedImage img = ImageLoader.loadCompatibleImage("Lemonade Stand.png");
  private BufferedImage img2 = ImageLoader.loadCompatibleImage("SB.png");
  private BufferedImage img3 = ImageLoader.loadCompatibleImage("Chain Store.png");
  private BufferedImage img4 = ImageLoader.loadCompatibleImage("Stock.png");
  private BufferedImage img5 = ImageLoader.loadCompatibleImage("Hedge Fund.png");

  public ClientEditor()
  {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
    frame = new JFrame();
    frame.setResizable(false);
    frame.setType(Type.UTILITY);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Osama\\Pictures\\Money Bag.png"));
    frame.getContentPane().setBackground(Color.WHITE);
    frame.setTitle("Wall Street Hustler");
    frame.setBackground(Color.WHITE);
    frame.setBounds(0, 0, 500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {240,240};
    gridBagLayout.rowHeights = new int[] {75, 75, 75, 75, 75, 75};
    gridBagLayout.columnWeights = new double[] {0.0, 0.0};
    gridBagLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    frame.getContentPane().setLayout(gridBagLayout);
    
    lblCash = new JLabel("Cash: $10.00");
    lblCash.setBackground(Color.WHITE);
    lblCash.setHorizontalAlignment(SwingConstants.CENTER);
    GridBagConstraints gbc_lblCash = new GridBagConstraints();
    gbc_lblCash.fill = GridBagConstraints.BOTH;
    gbc_lblCash.insets = new Insets(0, 0, 5, 5);
    gbc_lblCash.gridx = 0;
    gbc_lblCash.gridy = 0;
    frame.getContentPane().add(lblCash, gbc_lblCash);
    
    lblCashPerDay = new JLabel("$" + df.format(cashPerSec.getMoneyPerSec()) + "/day");
    lblCashPerDay.setBackground(Color.WHITE);
    lblCashPerDay.setHorizontalAlignment(SwingConstants.CENTER);
    GridBagConstraints gbc_lblCashPerDay = new GridBagConstraints();
    gbc_lblCashPerDay.fill = GridBagConstraints.BOTH;
    gbc_lblCashPerDay.insets = new Insets(0, 0, 5, 0);
    gbc_lblCashPerDay.gridx = 1;
    gbc_lblCashPerDay.gridy = 0;
    frame.getContentPane().add(lblCashPerDay, gbc_lblCashPerDay);
    
    LSButton = new JButton("Lemonade Stands Owned: " + LS.getNumOwned() + " Price: $" + df.format(LS.getPrice()));
    LSButton.setIcon(new ImageIcon(img));
    event LSClicked = new event();
    LSButton.addActionListener(LSClicked);

    GridBagConstraints gbc_LSButton = new GridBagConstraints();
    gbc_LSButton.gridwidth = 2;
    gbc_LSButton.fill = GridBagConstraints.BOTH;
    gbc_LSButton.insets = new Insets(0, 0, 5, 0);
    gbc_LSButton.gridx = 0;
    gbc_LSButton.gridy = 1;
    frame.getContentPane().add(LSButton, gbc_LSButton);

    SBButton = new JButton("Small Businesses Owned: " + SB.getNumOwned() + " Price: $" + df.format(SB.getPrice()));
    SBButton.setIcon(new ImageIcon(img2));
    event2 SBClicked = new event2();
    SBButton.addActionListener(SBClicked);

    GridBagConstraints gbc_SBButton = new GridBagConstraints();
    gbc_SBButton.gridwidth = 2;
    gbc_SBButton.fill = GridBagConstraints.BOTH;
    gbc_SBButton.insets = new Insets(0, 0, 5, 0);
    gbc_SBButton.gridx = 0;
    gbc_SBButton.gridy = 2;
    frame.getContentPane().add(SBButton, gbc_SBButton);

    CSButton = new JButton("Chain Stores Owned: " + CS.getNumOwned() + " Price: " + df.format(CS.getPrice()));
    CSButton.setIcon(new ImageIcon(img3));
    event3 CSClicked = new event3();
    CSButton.addActionListener(CSClicked);

    GridBagConstraints gbc_CSButton = new GridBagConstraints();
    gbc_CSButton.gridwidth = 2;
    gbc_CSButton.fill = GridBagConstraints.BOTH;
    gbc_CSButton.insets = new Insets(0, 0, 5, 0);
    gbc_CSButton.gridx = 0;
    gbc_CSButton.gridy = 3;
    frame.getContentPane().add(CSButton, gbc_CSButton);

    SButton = new JButton("Stock Owned: " + S.getNumOwned() + " Price: $" + df.format(S.getPrice()));
    SButton.setIcon(new ImageIcon(img4));
    event4 SClicked = new event4();
    SButton.addActionListener(SClicked);

    GridBagConstraints gbc_SButton = new GridBagConstraints();
    gbc_SButton.gridwidth = 2;
    gbc_SButton.fill = GridBagConstraints.BOTH;
    gbc_SButton.insets = new Insets(0, 0, 5, 0);
    gbc_SButton.gridx = 0;
    gbc_SButton.gridy = 4;
    frame.getContentPane().add(SButton, gbc_SButton);

    HFButton = new JButton("Hedge Funds Owned: " + HF.getNumOwned() + " Price:  $" + df.format(HF.getPrice()));
    HFButton.setIcon(new ImageIcon(img5));
    event5 HFClicked = new event5();
    HFButton.addActionListener(HFClicked);

    GridBagConstraints gbc_HFButton = new GridBagConstraints();
    gbc_HFButton.gridwidth = 2;
    gbc_HFButton.fill = GridBagConstraints.BOTH;
    gbc_HFButton.gridx = 0;
    gbc_HFButton.gridy = 5;
    frame.getContentPane().add(HFButton, gbc_HFButton);
  }

  public class event implements ActionListener
  {
    public void actionPerformed(ActionEvent LSClicked)
    {
      if (LS.getPrice() > cash)
      {
      }
      else if (cash >= LS.getPrice())
      {
        cash -= LS.getPrice();
        LS.getItems();
        cashPerSec.setMoneyPerSec();
        lblCash.setText("Cash: $" + df.format(cash));
        lblCashPerDay.setText("$" + df.format(cashPerSec.getMoneyPerSec()) + "/day");
        LSButton.setText("Lemonade Stands Owned: " + LS.getNumOwned() + " Price: $" + df.format(LS.getPrice()));
      }
    }
  }

  public class event2 implements ActionListener
  {
    public void actionPerformed(ActionEvent SBClicked)
    {
      if (SB.getPrice() > cash)
      {
      }
      else if (cash >= SB.getPrice())
      {
        cash -= SB.getPrice();
        SB.getItems();
        cashPerSec.setMoneyPerSec();
        lblCash.setText("Cash: $" + df.format(cash));
        lblCashPerDay.setText("$" + df.format(cashPerSec.getMoneyPerSec()) + "/day");
        SBButton.setText("Lemonade Stands Owned: " + SB.getNumOwned() + " Price: $" + df.format(SB.getPrice()));
      }
    }
  }

  public class event3 implements ActionListener
  {
    public void actionPerformed(ActionEvent CSClicked)
    {
      if (CS.getPrice() > cash)
      {
      }
      else if (cash >= CS.getPrice())
      {
        cash -= CS.getPrice();
        CS.getItems();
        cashPerSec.setMoneyPerSec();
        lblCash.setText("Cash: $" + df.format(cash));
        lblCashPerDay.setText("$" + df.format(cashPerSec.getMoneyPerSec()) + "/day");
        CSButton.setText("Lemonade Stands Owned: " + CS.getNumOwned() + " Price: $" + df.format(CS.getPrice()));
      }
    }
  }

  public class event4 implements ActionListener
  {
    public void actionPerformed(ActionEvent SClicked)
    {
      if (S.getPrice() > cash)
      {
      }
      else if (cash >= S.getPrice())
      {
        cash -= S.getPrice();
        S.getItems();
        cashPerSec.setMoneyPerSec();
        lblCash.setText("Cash: $" + df.format(cash));
        lblCashPerDay.setText("$" + df.format(cashPerSec.getMoneyPerSec()) + "/day");
        SButton.setText("Lemonade Stands Owned: " + S.getNumOwned() + " Price: $" + df.format(S.getPrice()));
      }
    }
  }

  public class event5 implements ActionListener
  {
    public void actionPerformed(ActionEvent HFClicked)
    {
      if (HF.getPrice() > cash)
      {
      }
      else if (cash >= HF.getPrice())
      {
        cash -= HF.getPrice();
        HF.getItems();
        cashPerSec.setMoneyPerSec();
        lblCash.setText("Cash: $" + df.format(cash));
        lblCashPerDay.setText("$" + df.format(cashPerSec.getMoneyPerSec()) + "/day");
        HFButton.setText("Lemonade Stands Owned: " + HF.getNumOwned() + " Price: $" + df.format(HF.getPrice()));
      }
    }
  }

  public class Earnings
  {
    double moneyPerSec;

    public double getMoneyPerSec()
    {
      return moneyPerSec;
    }

    public void setMoneyPerSec()
    {
      moneyPerSec = 0;
      if (LS.getNumOwned() > 0)
      {
        moneyPerSec += (Math.pow(1.15, LS.getNumOwned()));
      }
      if (SB.getNumOwned() > 0)
      {
        moneyPerSec += 10 * (Math.pow(1.15, SB.getNumOwned()));
      }
      if (CS.getNumOwned() > 0)
      {
        moneyPerSec += 100 * (Math.pow(1.15, CS.getNumOwned()));
      }
      if (S.getNumOwned() > 0)
      {
        moneyPerSec += 1000 * (Math.pow(1.15, S.getNumOwned()));
      }
      if (HF.getNumOwned() > 0)
      {
        moneyPerSec += 10000 * (Math.pow(1.15, HF.getNumOwned()));
      }
      moneyPerSec = Math.round(moneyPerSec * 100) / 100.0;
    }
  }

  TimerTask task = new TimerTask()
  {
    public void run()
    {
      cash += (cashPerSec.getMoneyPerSec() / 30);
      cash = ((int) (cash * 100)) / 100.0;
      lblCash.setText("Cash: $" + df.format(cash));
      lblCashPerDay.setText("$" + df.format(cashPerSec.getMoneyPerSec()) + "/day");
      if (cash < LS.getPrice())
      {
        LSButton.setBackground(Color.GRAY);
        LSButton.setForeground(Color.WHITE);
      }
      else if (cash >= LS.getPrice())
      {
        LSButton.setBackground(Color.WHITE);
        LSButton.setForeground(Color.BLACK);
      }
      if (cash < SB.getPrice())
      {
        SBButton.setBackground(Color.GRAY);
        SBButton.setForeground(Color.WHITE);
      }
      else if (cash >= SB.getPrice())
      {
        SBButton.setBackground(Color.WHITE);
        SBButton.setForeground(Color.BLACK);
      }
      if (cash < CS.getPrice())
      {
        CSButton.setBackground(Color.GRAY);
        CSButton.setForeground(Color.WHITE);
      }
      else if (cash >= CS.getPrice())
      {
        CSButton.setBackground(Color.WHITE);
        CSButton.setForeground(Color.BLACK);
      }
      if (cash < S.getPrice())
      {
        SButton.setBackground(Color.GRAY);
        SButton.setForeground(Color.WHITE);
      }
      else if (cash >= S.getPrice())
      {
        SButton.setBackground(Color.WHITE);
        SButton.setForeground(Color.BLACK);
      }
      if (cash < HF.getPrice())
      {
        HFButton.setBackground(Color.GRAY);
        HFButton.setForeground(Color.WHITE);
      }
      else if (cash >= HF.getPrice())
      {
        HFButton.setBackground(Color.WHITE);
        HFButton.setForeground(Color.BLACK);
      }
    }
  };

  public void start()
  {
    timer.scheduleAtFixedRate(task, 0, 1000 / 30);
  }
}