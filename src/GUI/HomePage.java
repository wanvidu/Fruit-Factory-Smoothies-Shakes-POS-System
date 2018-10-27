package GUI;

import DatabaseLayer.DatabaseConnection;
import com.sun.glass.events.KeyEvent;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HomePage extends javax.swing.JFrame
{

    DatabaseConnection conn;

    int orderID;

    int itemCount;

    int total;

    public HomePage()
    {
        initComponents();

        conn = DatabaseConnection.getSingleConnection();

        setPanels();

        displayTable();

        displayDelivaryTable();

        displayReportTable();

    }

    void setPanels()
    {
        jPanelCheckOut.setVisible(true);

        jPanelDelivery.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        jPanelCheckOut1.setVisible(false);
        jPanelCheckOut2.setVisible(false);

        jPanelDelivery1.setVisible(false);
        jPanelDelivery2.setVisible(false);
        jPanelDelivery3.setVisible(false);

        jPanelCheckOutN.setBackground(new java.awt.Color(66, 131, 221));
    }

    void displayTable()
    {
        Font f = new Font("Arial", Font.BOLD, 18);
        JTableHeader header = jTableItemList.getTableHeader();
        header.setFont(f);

        try
        {
            String item = "SELECT * FROM RecipeNames123456789";

            conn.get(item);

            while (conn.rs.next())
            {
                String name = conn.rs.getString("Name");

                Object[] content =
                {
                    name
                };

                DefaultTableModel model = (DefaultTableModel) jTableItemList.getModel();

                model.addRow(content);
            }
        } catch (Exception e)
        {
        }

    }

    void displayDelivaryTable()
    {
        Font f = new Font("Arial", Font.BOLD, 18);
        JTableHeader header = jTableItemListDelivery.getTableHeader();
        header.setFont(f);

        try
        {
            String item = "SELECT * FROM RecipeNames123456789";

            conn.get(item);

            while (conn.rs.next())
            {
                String name = conn.rs.getString("Name");

                Object[] content =
                {
                    name
                };

                DefaultTableModel model = (DefaultTableModel) jTableItemListDelivery.getModel();

                model.addRow(content);
            }
        } catch (Exception e)
        {
        }

    }

    void displayReportTable()
    {
        Font f = new Font("Arial", Font.BOLD, 18);
        JTableHeader header = jTableIReport.getTableHeader();
        header.setFont(f);
        
        try
        {
            String item = "SELECT o.OrderID AS 'OrderID',SUM(r.UnitPrice*o.ItemQuantity) AS 'OrderSUM', SUM(i2.UnitPrice*i1.Quantity) AS 'IngredientsCost' "
                    + "FROM Order123456789 o, Item123456789 i1, Ingredients123456789 i2,RecipeNames123456789 r "
                    + "WHERE o.ItemID=i1.RecipeID AND i1.RecipeID=r.RecipeID AND i1.IngredientID=i2.IngredientID "
                    + "GROUP BY o.OrderID,r.RecipeID,i1.IngredientID";

            conn.get(item);

            System.out.println(item);

            while (conn.rs.next())
            {
                int OrderID = Integer.parseInt(conn.rs.getString("OrderID"));
                int OrderSUM = Integer.parseInt(conn.rs.getString("OrderSUM"));
                int IngredientsCost = Integer.parseInt(conn.rs.getString("IngredientsCost"));
                int profit = OrderSUM - IngredientsCost;

                Object[] content =
                {
                    OrderID, OrderSUM, IngredientsCost, profit
                };

                DefaultTableModel model = (DefaultTableModel) jTableIReport.getModel();

                model.addRow(content);
            }
        } catch (Exception e)
        {
        }

    }

    void setTextFieldFirstPart()
    {
        String line = "-------------------------------------------------------------------------------------------\n";

        String shopName = "\n                        Fruit Factory Smoothies & Shakes POS\n\t                    Rajagiriya\n\t                   0714589632\n\n";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String dateTime = dtf.format(now);

        String columeHeaders = "NO            ITEM                QTY                PRICE               AMOUNT";

        jTextAreaJPanelCheckOut.setText(line + shopName + dateTime + "\n" + line + columeHeaders + "\n" + line);
    }

    void setTextFieldSecondPart()
    {
        String line = "-------------------------------------------------------------------------------------------\n";

        String Total = "Net Total";

        String notice = "\n------------------------------HOTLINE-----------------------------------------------\n"
                + "                 Please call our hotline 0714589632 for your \n"
                + "                        valued suggestions and comments";

        jTextAreaJPanelCheckOut.append("\n" + line + Total + "\t\t\t          " + total + ".00" + notice);
    }

    void setTextFieldDeliveryFirstPart()
    {
        String line = "-------------------------------------------------------------------------------------------\n";

        String shopName = "\n                        Fruit Factory Smoothies & Shakes POS\n\t                    Rajagiriya\n\t                   0714589632\n\n";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String dateTime = dtf.format(now);

        String columeHeaders = "NO            ITEM                QTY                PRICE               AMOUNT";

        jTextAreaJPanelDelivery.setText(line + shopName + dateTime + "\n" + line + columeHeaders + "\n" + line);
    }

    void setTextFieldDeliverySecondPart()
    {
        String line = "-------------------------------------------------------------------------------------------\n";

        String Total = "Net Total";

        String cusDetails = "Customer Name : " + jTextFieldDeliveryCusName.getText() + "\n"
                + "Address : " + jTextFieldDeliveryCusAddress.getText() + "\n"
                + "Phone Number : " + jTextFieldDeliveryCusPhone.getText() + "\n";

        String notice = "\n------------------------------HOTLINE-----------------------------------------------\n"
                + "                 Please call our hotline 0714589632 for your \n"
                + "                        valued suggestions and comments";

        jTextAreaJPanelDeliveryPrint.append("\n" + line + Total + "\t\t\t          " + total + ".00\n" + line + cusDetails + notice);

    }

    void setEnabledAll(Container container, boolean enabled)
    {
        Component[] components = container.getComponents();

        if (components.length > 0)
        {
            for (Component component : components)
            {
                component.setEnabled(enabled);
                if (component instanceof Container)
                {
                    setEnabledAll((Container) component, enabled);
                }
            }
        }
    }

    void buttonEnabler(boolean status)
    {
        jButtonNewOrder.setEnabled(status);
        jButtonNewDeliveryOrder.setEnabled(status);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogKeyPad = new javax.swing.JDialog();
        jLabelKeyPadDisplay = new javax.swing.JLabel();
        jButtonKeyPadDelete = new javax.swing.JButton();
        jButtonKeyPad0 = new javax.swing.JButton();
        jButtonKeyPadAC = new javax.swing.JButton();
        jButtonKeyPad1 = new javax.swing.JButton();
        jButtonKeyPad2 = new javax.swing.JButton();
        jButtonKeyPad3 = new javax.swing.JButton();
        jButtonKeyPad6 = new javax.swing.JButton();
        jButtonKeyPad4 = new javax.swing.JButton();
        jButtonKeyPad5 = new javax.swing.JButton();
        jButtonKeyPad9 = new javax.swing.JButton();
        jButtonKeyPad8 = new javax.swing.JButton();
        jButtonKeyPad7 = new javax.swing.JButton();
        jButtonKeyPadADD = new javax.swing.JButton();
        jDialogDeliveryKeyPad = new javax.swing.JDialog();
        jLabelDeliveryKeyPadDisplay = new javax.swing.JLabel();
        jButtonDeliveryKeyPadDelete = new javax.swing.JButton();
        jButtonDeliveryKeyPad0 = new javax.swing.JButton();
        jButtonDeliveryKeyPadAC = new javax.swing.JButton();
        jButtonDeliveryKeyPad1 = new javax.swing.JButton();
        jButtonDeliveryKeyPad2 = new javax.swing.JButton();
        jButtonDeliveryKeyPad3 = new javax.swing.JButton();
        jButtonDeliveryKeyPad6 = new javax.swing.JButton();
        jButtonDeliveryKeyPad4 = new javax.swing.JButton();
        jButtonDeliveryKeyPad5 = new javax.swing.JButton();
        jButtonDeliveryKeyPad9 = new javax.swing.JButton();
        jButtonDeliveryKeyPad8 = new javax.swing.JButton();
        jButtonDeliveryKeyPad7 = new javax.swing.JButton();
        jButtonKeyPadADD1 = new javax.swing.JButton();
        jPanelTopPanel = new javax.swing.JPanel();
        jPanelSidePanel = new javax.swing.JPanel();
        jPanelCheckOutN = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanelDeliveryN = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanelSuppliersN = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanelReportN = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanelCheckOut = new javax.swing.JPanel();
        jButtonNewOrder = new javax.swing.JButton();
        jPanelCheckOut1 = new javax.swing.JPanel();
        jLabeljPanelCheckOutTotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItemList = new javax.swing.JTable();
        jButtonjPanelChechOutDone = new javax.swing.JButton();
        jButtonjPanelChechOutCancle = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaJPanelCheckOut = new javax.swing.JTextArea();
        jPanelCheckOut2 = new javax.swing.JPanel();
        jButtonjPanelChechOutPrint = new javax.swing.JButton();
        jButtonjPanelChechOutCancle1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaJPanelPrint = new javax.swing.JTextArea();
        jPanelDelivery = new javax.swing.JPanel();
        jButtonNewDeliveryOrder = new javax.swing.JButton();
        jPanelDelivery1 = new javax.swing.JPanel();
        jLabeljPanelDeliveryTotal = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableItemListDelivery = new javax.swing.JTable();
        jButtonjPanelDeliveryDone = new javax.swing.JButton();
        jButtonjPanelDeliveryCancle = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaJPanelDelivery = new javax.swing.JTextArea();
        jPanelDelivery2 = new javax.swing.JPanel();
        jButtonjPanelDeliveryCustomerNext = new javax.swing.JButton();
        jButtonjPanelDeliveryCancle1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaJPanelDeliveryCustomer = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDeliveryCusID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDeliveryCusName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldDeliveryCusAddress = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldDeliveryCusPhone = new javax.swing.JTextField();
        jPanelDelivery3 = new javax.swing.JPanel();
        jButtonjPanelDeliveryPrint = new javax.swing.JButton();
        jButtonjPanelDeliveryCancel2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaJPanelDeliveryPrint = new javax.swing.JTextArea();
        jPanelSuppliers = new javax.swing.JPanel();
        jButtonManageSuppliers = new javax.swing.JButton();
        jPanelReport = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableIReport = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemLogOut = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemManageEmployees = new javax.swing.JMenuItem();
        jMenuItemManageItems = new javax.swing.JMenuItem();
        jMenuItemManageIngredients = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();

        jDialogKeyPad.setTitle("Add Quantity");
        jDialogKeyPad.setLocationByPlatform(true);
        jDialogKeyPad.setMinimumSize(new java.awt.Dimension(400, 500));
        jDialogKeyPad.setResizable(false);

        jLabelKeyPadDisplay.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelKeyPadDisplay.setToolTipText("");
        jLabelKeyPadDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jButtonKeyPadDelete.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPadDelete.setText("Delete");
        jButtonKeyPadDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPadDeleteActionPerformed(evt);
            }
        });

        jButtonKeyPad0.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad0.setText("0");
        jButtonKeyPad0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad0ActionPerformed(evt);
            }
        });

        jButtonKeyPadAC.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPadAC.setText("AC");
        jButtonKeyPadAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPadACActionPerformed(evt);
            }
        });

        jButtonKeyPad1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad1.setText("1");
        jButtonKeyPad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad1ActionPerformed(evt);
            }
        });

        jButtonKeyPad2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad2.setText("2");
        jButtonKeyPad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad2ActionPerformed(evt);
            }
        });

        jButtonKeyPad3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad3.setText("3");
        jButtonKeyPad3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad3ActionPerformed(evt);
            }
        });

        jButtonKeyPad6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad6.setText("6");
        jButtonKeyPad6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad6ActionPerformed(evt);
            }
        });

        jButtonKeyPad4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad4.setText("4");
        jButtonKeyPad4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad4ActionPerformed(evt);
            }
        });

        jButtonKeyPad5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad5.setText("5");
        jButtonKeyPad5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad5ActionPerformed(evt);
            }
        });

        jButtonKeyPad9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad9.setText("9");
        jButtonKeyPad9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad9ActionPerformed(evt);
            }
        });

        jButtonKeyPad8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad8.setText("8");
        jButtonKeyPad8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad8ActionPerformed(evt);
            }
        });

        jButtonKeyPad7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPad7.setText("7");
        jButtonKeyPad7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPad7ActionPerformed(evt);
            }
        });

        jButtonKeyPadADD.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPadADD.setText("ADD");
        jButtonKeyPadADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPadADDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogKeyPadLayout = new javax.swing.GroupLayout(jDialogKeyPad.getContentPane());
        jDialogKeyPad.getContentPane().setLayout(jDialogKeyPadLayout);
        jDialogKeyPadLayout.setHorizontalGroup(
            jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogKeyPadLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonKeyPadADD, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelKeyPadDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jDialogKeyPadLayout.createSequentialGroup()
                            .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jDialogKeyPadLayout.createSequentialGroup()
                                    .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButtonKeyPad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonKeyPadDelete))
                                    .addGap(18, 18, 18)
                                    .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonKeyPad0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonKeyPad2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jDialogKeyPadLayout.createSequentialGroup()
                                    .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonKeyPad4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonKeyPad7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonKeyPad8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonKeyPad5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(18, 18, 18)
                            .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonKeyPad9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonKeyPadAC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonKeyPad3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonKeyPad6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jDialogKeyPadLayout.setVerticalGroup(
            jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogKeyPadLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabelKeyPadDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogKeyPadLayout.createSequentialGroup()
                        .addComponent(jButtonKeyPadAC)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonKeyPad3))
                    .addGroup(jDialogKeyPadLayout.createSequentialGroup()
                        .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonKeyPadDelete)
                            .addComponent(jButtonKeyPad0))
                        .addGap(18, 18, 18)
                        .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonKeyPad1)
                            .addComponent(jButtonKeyPad2))))
                .addGap(18, 18, 18)
                .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKeyPad5)
                    .addComponent(jButtonKeyPad6)
                    .addComponent(jButtonKeyPad4))
                .addGap(18, 18, 18)
                .addGroup(jDialogKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKeyPad9)
                    .addComponent(jButtonKeyPad8)
                    .addComponent(jButtonKeyPad7))
                .addGap(18, 18, 18)
                .addComponent(jButtonKeyPadADD)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jDialogDeliveryKeyPad.setTitle("Add Quantity");
        jDialogDeliveryKeyPad.setLocationByPlatform(true);
        jDialogDeliveryKeyPad.setMinimumSize(new java.awt.Dimension(400, 500));
        jDialogDeliveryKeyPad.setResizable(false);

        jLabelDeliveryKeyPadDisplay.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelDeliveryKeyPadDisplay.setToolTipText("");
        jLabelDeliveryKeyPadDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jButtonDeliveryKeyPadDelete.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPadDelete.setText("Delete");
        jButtonDeliveryKeyPadDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPadDeleteActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad0.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad0.setText("0");
        jButtonDeliveryKeyPad0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad0ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPadAC.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPadAC.setText("AC");
        jButtonDeliveryKeyPadAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPadACActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad1.setText("1");
        jButtonDeliveryKeyPad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad1ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad2.setText("2");
        jButtonDeliveryKeyPad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad2ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad3.setText("3");
        jButtonDeliveryKeyPad3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad3ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad6.setText("6");
        jButtonDeliveryKeyPad6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad6ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad4.setText("4");
        jButtonDeliveryKeyPad4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad4ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad5.setText("5");
        jButtonDeliveryKeyPad5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad5ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad9.setText("9");
        jButtonDeliveryKeyPad9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad9ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad8.setText("8");
        jButtonDeliveryKeyPad8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad8ActionPerformed(evt);
            }
        });

        jButtonDeliveryKeyPad7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonDeliveryKeyPad7.setText("7");
        jButtonDeliveryKeyPad7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeliveryKeyPad7ActionPerformed(evt);
            }
        });

        jButtonKeyPadADD1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonKeyPadADD1.setText("ADD");
        jButtonKeyPadADD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyPadADD1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogDeliveryKeyPadLayout = new javax.swing.GroupLayout(jDialogDeliveryKeyPad.getContentPane());
        jDialogDeliveryKeyPad.getContentPane().setLayout(jDialogDeliveryKeyPadLayout);
        jDialogDeliveryKeyPadLayout.setHorizontalGroup(
            jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogDeliveryKeyPadLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonKeyPadADD1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelDeliveryKeyPadDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jDialogDeliveryKeyPadLayout.createSequentialGroup()
                            .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jDialogDeliveryKeyPadLayout.createSequentialGroup()
                                    .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButtonDeliveryKeyPad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonDeliveryKeyPadDelete))
                                    .addGap(18, 18, 18)
                                    .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonDeliveryKeyPad0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonDeliveryKeyPad2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jDialogDeliveryKeyPadLayout.createSequentialGroup()
                                    .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonDeliveryKeyPad4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonDeliveryKeyPad7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonDeliveryKeyPad8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonDeliveryKeyPad5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(18, 18, 18)
                            .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonDeliveryKeyPad9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDeliveryKeyPadAC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDeliveryKeyPad3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDeliveryKeyPad6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jDialogDeliveryKeyPadLayout.setVerticalGroup(
            jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogDeliveryKeyPadLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabelDeliveryKeyPadDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogDeliveryKeyPadLayout.createSequentialGroup()
                        .addComponent(jButtonDeliveryKeyPadAC)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDeliveryKeyPad3))
                    .addGroup(jDialogDeliveryKeyPadLayout.createSequentialGroup()
                        .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDeliveryKeyPadDelete)
                            .addComponent(jButtonDeliveryKeyPad0))
                        .addGap(18, 18, 18)
                        .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDeliveryKeyPad1)
                            .addComponent(jButtonDeliveryKeyPad2))))
                .addGap(18, 18, 18)
                .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeliveryKeyPad5)
                    .addComponent(jButtonDeliveryKeyPad6)
                    .addComponent(jButtonDeliveryKeyPad4))
                .addGap(18, 18, 18)
                .addGroup(jDialogDeliveryKeyPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeliveryKeyPad9)
                    .addComponent(jButtonDeliveryKeyPad8)
                    .addComponent(jButtonDeliveryKeyPad7))
                .addGap(18, 18, 18)
                .addComponent(jButtonKeyPadADD1)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fruit Factory Smoothies & Shakes POS System");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanelTopPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanelTopPanelLayout = new javax.swing.GroupLayout(jPanelTopPanel);
        jPanelTopPanel.setLayout(jPanelTopPanelLayout);
        jPanelTopPanelLayout.setHorizontalGroup(
            jPanelTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        jPanelTopPanelLayout.setVerticalGroup(
            jPanelTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelTopPanel);
        jPanelTopPanel.setBounds(0, 0, 1200, 69);

        jPanelSidePanel.setBackground(new java.awt.Color(51, 51, 51));

        jPanelCheckOutN.setBackground(new java.awt.Color(51, 51, 51));
        jPanelCheckOutN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanelCheckOutN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCheckOutNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCheckOutNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCheckOutNMouseExited(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Check Out");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Pictures/icons8_Shopping_Cart_50px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanelCheckOutNLayout = new javax.swing.GroupLayout(jPanelCheckOutN);
        jPanelCheckOutN.setLayout(jPanelCheckOutNLayout);
        jPanelCheckOutNLayout.setHorizontalGroup(
            jPanelCheckOutNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckOutNLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanelCheckOutNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27))
        );
        jPanelCheckOutNLayout.setVerticalGroup(
            jPanelCheckOutNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckOutNLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanelDeliveryN.setBackground(new java.awt.Color(51, 51, 51));
        jPanelDeliveryN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanelDeliveryN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelDeliveryNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelDeliveryNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelDeliveryNMouseExited(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Delivery");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Pictures/icons8_Truck_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanelDeliveryNLayout = new javax.swing.GroupLayout(jPanelDeliveryN);
        jPanelDeliveryN.setLayout(jPanelDeliveryNLayout);
        jPanelDeliveryNLayout.setHorizontalGroup(
            jPanelDeliveryNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDeliveryNLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDeliveryNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31))
        );
        jPanelDeliveryNLayout.setVerticalGroup(
            jPanelDeliveryNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDeliveryNLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelSuppliersN.setBackground(new java.awt.Color(51, 51, 51));
        jPanelSuppliersN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanelSuppliersN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSuppliersNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelSuppliersNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelSuppliersNMouseExited(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Suppliers");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Pictures/icons8_User_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanelSuppliersNLayout = new javax.swing.GroupLayout(jPanelSuppliersN);
        jPanelSuppliersN.setLayout(jPanelSuppliersNLayout);
        jPanelSuppliersNLayout.setHorizontalGroup(
            jPanelSuppliersNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuppliersNLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelSuppliersNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addGap(29, 29, 29))
        );
        jPanelSuppliersNLayout.setVerticalGroup(
            jPanelSuppliersNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuppliersNLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanelReportN.setBackground(new java.awt.Color(51, 51, 51));
        jPanelReportN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanelReportN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelReportNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelReportNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelReportNMouseExited(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Report");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Pictures/icons8_Bar_Chart_50px_1.png"))); // NOI18N
        jLabel12.setText("jLabel12");

        javax.swing.GroupLayout jPanelReportNLayout = new javax.swing.GroupLayout(jPanelReportN);
        jPanelReportN.setLayout(jPanelReportNLayout);
        jPanelReportNLayout.setHorizontalGroup(
            jPanelReportNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportNLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelReportNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReportNLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReportNLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanelReportNLayout.setVerticalGroup(
            jPanelReportNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReportNLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelSidePanelLayout = new javax.swing.GroupLayout(jPanelSidePanel);
        jPanelSidePanel.setLayout(jPanelSidePanelLayout);
        jPanelSidePanelLayout.setHorizontalGroup(
            jPanelSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSidePanelLayout.createSequentialGroup()
                        .addGroup(jPanelSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelReportN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelCheckOutN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelDeliveryN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelSidePanelLayout.createSequentialGroup()
                        .addComponent(jPanelSuppliersN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanelSidePanelLayout.setVerticalGroup(
            jPanelSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSidePanelLayout.createSequentialGroup()
                .addComponent(jPanelCheckOutN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDeliveryN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSuppliersN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelReportN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 282, Short.MAX_VALUE))
        );

        jPanelSidePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanelCheckOutN, jPanelDeliveryN, jPanelReportN, jPanelSuppliersN});

        getContentPane().add(jPanelSidePanel);
        jPanelSidePanel.setBounds(0, 69, 120, 724);

        jPanelCheckOut.setBackground(new java.awt.Color(204, 204, 255));
        jPanelCheckOut.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jButtonNewOrder.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonNewOrder.setText("New Order");
        jButtonNewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCheckOutLayout = new javax.swing.GroupLayout(jPanelCheckOut);
        jPanelCheckOut.setLayout(jPanelCheckOutLayout);
        jPanelCheckOutLayout.setHorizontalGroup(
            jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                .addGap(439, 439, 439)
                .addComponent(jButtonNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(439, Short.MAX_VALUE))
        );
        jPanelCheckOutLayout.setVerticalGroup(
            jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(jButtonNewOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelCheckOut);
        jPanelCheckOut.setBounds(120, 70, 1090, 710);

        jPanelCheckOut1.setBackground(new java.awt.Color(204, 204, 255));
        jPanelCheckOut1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jPanelCheckOut1.setLayout(null);

        jLabeljPanelCheckOutTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabeljPanelCheckOutTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCheckOut1.add(jLabeljPanelCheckOutTotal);
        jLabeljPanelCheckOutTotal.setBounds(910, 540, 140, 30);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("Total");
        jPanelCheckOut1.add(jLabel13);
        jLabel13.setBounds(840, 530, 138, 42);

        jTableItemList.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jTableItemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select an Item"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableItemList.setRowHeight(40);
        jTableItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableItemListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableItemList);

        jPanelCheckOut1.add(jScrollPane2);
        jScrollPane2.setBounds(20, 0, 422, 740);

        jButtonjPanelChechOutDone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelChechOutDone.setText("Done");
        jButtonjPanelChechOutDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelChechOutDoneActionPerformed(evt);
            }
        });
        jPanelCheckOut1.add(jButtonjPanelChechOutDone);
        jButtonjPanelChechOutDone.setBounds(776, 600, 120, 33);

        jButtonjPanelChechOutCancle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelChechOutCancle.setText("Cancle");
        jButtonjPanelChechOutCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelChechOutCancleActionPerformed(evt);
            }
        });
        jPanelCheckOut1.add(jButtonjPanelChechOutCancle);
        jButtonjPanelChechOutCancle.setBounds(930, 600, 124, 33);

        jTextAreaJPanelCheckOut.setColumns(20);
        jTextAreaJPanelCheckOut.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jTextAreaJPanelCheckOut.setRows(5);
        jScrollPane1.setViewportView(jTextAreaJPanelCheckOut);

        jPanelCheckOut1.add(jScrollPane1);
        jScrollPane1.setBounds(570, 10, 480, 500);

        getContentPane().add(jPanelCheckOut1);
        jPanelCheckOut1.setBounds(120, 70, 1090, 710);

        jPanelCheckOut2.setBackground(new java.awt.Color(204, 204, 255));
        jPanelCheckOut2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jPanelCheckOut2.setLayout(null);

        jButtonjPanelChechOutPrint.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelChechOutPrint.setText("Print");
        jButtonjPanelChechOutPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelChechOutPrintActionPerformed(evt);
            }
        });
        jPanelCheckOut2.add(jButtonjPanelChechOutPrint);
        jButtonjPanelChechOutPrint.setBounds(660, 600, 120, 33);

        jButtonjPanelChechOutCancle1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelChechOutCancle1.setText("Cancle");
        jButtonjPanelChechOutCancle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelChechOutCancle1ActionPerformed(evt);
            }
        });
        jPanelCheckOut2.add(jButtonjPanelChechOutCancle1);
        jButtonjPanelChechOutCancle1.setBounds(840, 600, 124, 33);

        jTextAreaJPanelPrint.setColumns(20);
        jTextAreaJPanelPrint.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jTextAreaJPanelPrint.setRows(5);
        jScrollPane5.setViewportView(jTextAreaJPanelPrint);

        jPanelCheckOut2.add(jScrollPane5);
        jScrollPane5.setBounds(30, 10, 480, 630);

        getContentPane().add(jPanelCheckOut2);
        jPanelCheckOut2.setBounds(120, 70, 1090, 710);

        jPanelDelivery.setBackground(new java.awt.Color(255, 204, 255));

        jButtonNewDeliveryOrder.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonNewDeliveryOrder.setText("New Delivery Order");
        jButtonNewDeliveryOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewDeliveryOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDeliveryLayout = new javax.swing.GroupLayout(jPanelDelivery);
        jPanelDelivery.setLayout(jPanelDeliveryLayout);
        jPanelDeliveryLayout.setHorizontalGroup(
            jPanelDeliveryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeliveryLayout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jButtonNewDeliveryOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(442, Short.MAX_VALUE))
        );
        jPanelDeliveryLayout.setVerticalGroup(
            jPanelDeliveryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeliveryLayout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(jButtonNewDeliveryOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelDelivery);
        jPanelDelivery.setBounds(120, 70, 1090, 710);

        jPanelDelivery1.setBackground(new java.awt.Color(204, 204, 255));
        jPanelDelivery1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jPanelDelivery1.setLayout(null);

        jLabeljPanelDeliveryTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabeljPanelDeliveryTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelDelivery1.add(jLabeljPanelDeliveryTotal);
        jLabeljPanelDeliveryTotal.setBounds(910, 540, 140, 30);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setText("Total");
        jPanelDelivery1.add(jLabel17);
        jLabel17.setBounds(840, 530, 138, 42);

        jTableItemListDelivery.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jTableItemListDelivery.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select an Item"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableItemListDelivery.setRowHeight(40);
        jTableItemListDelivery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableItemListDeliveryMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableItemListDelivery);

        jPanelDelivery1.add(jScrollPane3);
        jScrollPane3.setBounds(20, 0, 422, 740);

        jButtonjPanelDeliveryDone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelDeliveryDone.setText("Next");
        jButtonjPanelDeliveryDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelDeliveryDoneActionPerformed(evt);
            }
        });
        jPanelDelivery1.add(jButtonjPanelDeliveryDone);
        jButtonjPanelDeliveryDone.setBounds(776, 600, 120, 33);

        jButtonjPanelDeliveryCancle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelDeliveryCancle.setText("Cancle");
        jButtonjPanelDeliveryCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelDeliveryCancleActionPerformed(evt);
            }
        });
        jPanelDelivery1.add(jButtonjPanelDeliveryCancle);
        jButtonjPanelDeliveryCancle.setBounds(930, 600, 124, 33);

        jTextAreaJPanelDelivery.setColumns(20);
        jTextAreaJPanelDelivery.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jTextAreaJPanelDelivery.setRows(5);
        jScrollPane4.setViewportView(jTextAreaJPanelDelivery);

        jPanelDelivery1.add(jScrollPane4);
        jScrollPane4.setBounds(570, 10, 480, 500);

        getContentPane().add(jPanelDelivery1);
        jPanelDelivery1.setBounds(120, 70, 1090, 710);

        jPanelDelivery2.setBackground(new java.awt.Color(204, 204, 255));
        jPanelDelivery2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jPanelDelivery2.setLayout(null);

        jButtonjPanelDeliveryCustomerNext.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelDeliveryCustomerNext.setText("Next");
        jButtonjPanelDeliveryCustomerNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelDeliveryCustomerNextActionPerformed(evt);
            }
        });
        jPanelDelivery2.add(jButtonjPanelDeliveryCustomerNext);
        jButtonjPanelDeliveryCustomerNext.setBounds(776, 600, 120, 33);

        jButtonjPanelDeliveryCancle1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelDeliveryCancle1.setText("Cancle");
        jButtonjPanelDeliveryCancle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelDeliveryCancle1ActionPerformed(evt);
            }
        });
        jPanelDelivery2.add(jButtonjPanelDeliveryCancle1);
        jButtonjPanelDeliveryCancle1.setBounds(930, 600, 124, 33);

        jTextAreaJPanelDeliveryCustomer.setColumns(20);
        jTextAreaJPanelDeliveryCustomer.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jTextAreaJPanelDeliveryCustomer.setRows(5);
        jScrollPane8.setViewportView(jTextAreaJPanelDeliveryCustomer);

        jPanelDelivery2.add(jScrollPane8);
        jScrollPane8.setBounds(570, 10, 480, 500);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Customer ID");
        jPanelDelivery2.add(jLabel5);
        jLabel5.setBounds(50, 60, 150, 30);

        jTextFieldDeliveryCusID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldDeliveryCusID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDeliveryCusIDKeyTyped(evt);
            }
        });
        jPanelDelivery2.add(jTextFieldDeliveryCusID);
        jTextFieldDeliveryCusID.setBounds(230, 60, 220, 30);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Customer Name");
        jPanelDelivery2.add(jLabel6);
        jLabel6.setBounds(50, 110, 160, 30);

        jTextFieldDeliveryCusName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldDeliveryCusName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDeliveryCusNameKeyTyped(evt);
            }
        });
        jPanelDelivery2.add(jTextFieldDeliveryCusName);
        jTextFieldDeliveryCusName.setBounds(230, 110, 220, 30);

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setText("Address");
        jPanelDelivery2.add(jLabel16);
        jLabel16.setBounds(50, 160, 120, 30);

        jTextFieldDeliveryCusAddress.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldDeliveryCusAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDeliveryCusAddressKeyTyped(evt);
            }
        });
        jPanelDelivery2.add(jTextFieldDeliveryCusAddress);
        jTextFieldDeliveryCusAddress.setBounds(230, 160, 220, 30);

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setText("Phone Number");
        jPanelDelivery2.add(jLabel18);
        jLabel18.setBounds(50, 210, 160, 30);

        jTextFieldDeliveryCusPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextFieldDeliveryCusPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDeliveryCusPhoneKeyTyped(evt);
            }
        });
        jPanelDelivery2.add(jTextFieldDeliveryCusPhone);
        jTextFieldDeliveryCusPhone.setBounds(230, 210, 220, 30);

        getContentPane().add(jPanelDelivery2);
        jPanelDelivery2.setBounds(120, 70, 1090, 710);

        jPanelDelivery3.setBackground(new java.awt.Color(204, 204, 255));
        jPanelDelivery3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jPanelDelivery3.setLayout(null);

        jButtonjPanelDeliveryPrint.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelDeliveryPrint.setText("Print");
        jButtonjPanelDeliveryPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelDeliveryPrintActionPerformed(evt);
            }
        });
        jPanelDelivery3.add(jButtonjPanelDeliveryPrint);
        jButtonjPanelDeliveryPrint.setBounds(660, 600, 120, 33);

        jButtonjPanelDeliveryCancel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonjPanelDeliveryCancel2.setText("Cancle");
        jButtonjPanelDeliveryCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonjPanelDeliveryCancel2ActionPerformed(evt);
            }
        });
        jPanelDelivery3.add(jButtonjPanelDeliveryCancel2);
        jButtonjPanelDeliveryCancel2.setBounds(840, 600, 124, 33);

        jTextAreaJPanelDeliveryPrint.setColumns(20);
        jTextAreaJPanelDeliveryPrint.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jTextAreaJPanelDeliveryPrint.setRows(5);
        jScrollPane6.setViewportView(jTextAreaJPanelDeliveryPrint);

        jPanelDelivery3.add(jScrollPane6);
        jScrollPane6.setBounds(30, 10, 480, 630);

        getContentPane().add(jPanelDelivery3);
        jPanelDelivery3.setBounds(120, 70, 1090, 710);

        jPanelSuppliers.setBackground(new java.awt.Color(255, 255, 204));

        jButtonManageSuppliers.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonManageSuppliers.setText("Manage Suppliers");
        jButtonManageSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManageSuppliersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSuppliersLayout = new javax.swing.GroupLayout(jPanelSuppliers);
        jPanelSuppliers.setLayout(jPanelSuppliersLayout);
        jPanelSuppliersLayout.setHorizontalGroup(
            jPanelSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuppliersLayout.createSequentialGroup()
                .addGap(439, 439, 439)
                .addComponent(jButtonManageSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(439, Short.MAX_VALUE))
        );
        jPanelSuppliersLayout.setVerticalGroup(
            jPanelSuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuppliersLayout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(jButtonManageSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelSuppliers);
        jPanelSuppliers.setBounds(120, 70, 1090, 710);

        jPanelReport.setBackground(new java.awt.Color(204, 255, 204));

        jTableIReport.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jTableIReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "OrderSUM", "IngredientsCost", "Profit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableIReport.setRowHeight(40);
        jTableIReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableIReportMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableIReport);

        javax.swing.GroupLayout jPanelReportLayout = new javax.swing.GroupLayout(jPanelReport);
        jPanelReport.setLayout(jPanelReportLayout);
        jPanelReportLayout.setHorizontalGroup(
            jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        jPanelReportLayout.setVerticalGroup(
            jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelReport);
        jPanelReport.setBounds(120, 70, 1090, 710);

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        jMenuItemLogOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemLogOut.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jMenuItemLogOut.setText("LogOut");
        jMenuItemLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogOutActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemLogOut);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemExit.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        jMenuItemManageEmployees.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemManageEmployees.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jMenuItemManageEmployees.setText("Manage Employees");
        jMenuItemManageEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemManageEmployeesActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemManageEmployees);

        jMenuItemManageItems.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemManageItems.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jMenuItemManageItems.setText("Manage Items");
        jMenuItemManageItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemManageItemsActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemManageItems);

        jMenuItemManageIngredients.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemManageIngredients.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jMenuItemManageIngredients.setText("Manage Ingredients");
        jMenuItemManageIngredients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemManageIngredientsActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemManageIngredients);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");
        jMenu3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jMenuItem1.setText("View Help");
        jMenu3.add(jMenuItem1);
        jMenu3.add(jSeparator1);

        jMenuItem3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jMenuItem3.setText("About");
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelCheckOutNMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelCheckOutNMouseEntered
    {//GEN-HEADEREND:event_jPanelCheckOutNMouseEntered
        if (!jPanelCheckOut.isVisible())
        {
            jPanelCheckOutN.setBackground(new java.awt.Color(58, 58, 58));
        }
    }//GEN-LAST:event_jPanelCheckOutNMouseEntered

    private void jPanelCheckOutNMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelCheckOutNMouseExited
    {//GEN-HEADEREND:event_jPanelCheckOutNMouseExited
        if (!jPanelCheckOut.isVisible())
        {
            jPanelCheckOutN.setBackground(new java.awt.Color(51, 51, 51));
        }
    }//GEN-LAST:event_jPanelCheckOutNMouseExited

    private void jPanelDeliveryNMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelDeliveryNMouseEntered
    {//GEN-HEADEREND:event_jPanelDeliveryNMouseEntered
        if (!jPanelDelivery.isVisible())
        {
            jPanelDeliveryN.setBackground(new java.awt.Color(58, 58, 58));
        }
    }//GEN-LAST:event_jPanelDeliveryNMouseEntered

    private void jPanelDeliveryNMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelDeliveryNMouseExited
    {//GEN-HEADEREND:event_jPanelDeliveryNMouseExited
        if (!jPanelDelivery.isVisible())
        {
            jPanelDeliveryN.setBackground(new java.awt.Color(51, 51, 51));
        }
    }//GEN-LAST:event_jPanelDeliveryNMouseExited

    private void jPanelDeliveryNMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelDeliveryNMouseClicked
    {//GEN-HEADEREND:event_jPanelDeliveryNMouseClicked

        if (jButtonNewOrder.isEnabled() && jButtonNewDeliveryOrder.isEnabled())
        {
            jPanelDelivery.setVisible(true);

            jPanelCheckOut.setVisible(false);
            jPanelReport.setVisible(false);
            jPanelSuppliers.setVisible(false);

            jPanelCheckOut1.setVisible(false);
            jPanelDelivery1.setVisible(false);

            jPanelDeliveryN.setBackground(new java.awt.Color(66, 131, 221));
            jPanelCheckOutN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelSuppliersN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelReportN.setBackground(new java.awt.Color(51, 51, 51));
        }
    }//GEN-LAST:event_jPanelDeliveryNMouseClicked

    private void jPanelSuppliersNMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelSuppliersNMouseEntered
    {//GEN-HEADEREND:event_jPanelSuppliersNMouseEntered
        if (!jPanelSuppliers.isVisible())
        {
            jPanelSuppliersN.setBackground(new java.awt.Color(58, 58, 58));
        }
    }//GEN-LAST:event_jPanelSuppliersNMouseEntered

    private void jPanelSuppliersNMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelSuppliersNMouseExited
    {//GEN-HEADEREND:event_jPanelSuppliersNMouseExited
        if (!jPanelSuppliers.isVisible())
        {
            jPanelSuppliersN.setBackground(new java.awt.Color(51, 51, 51));
        }
    }//GEN-LAST:event_jPanelSuppliersNMouseExited

    private void jPanelReportNMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelReportNMouseEntered
    {//GEN-HEADEREND:event_jPanelReportNMouseEntered
        if (!jPanelReport.isVisible())
        {
            jPanelReportN.setBackground(new java.awt.Color(58, 58, 58));
        }
    }//GEN-LAST:event_jPanelReportNMouseEntered

    private void jPanelReportNMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelReportNMouseExited
    {//GEN-HEADEREND:event_jPanelReportNMouseExited
        if (!jPanelReport.isVisible())
        {
            jPanelReportN.setBackground(new java.awt.Color(51, 51, 51));
        }
    }//GEN-LAST:event_jPanelReportNMouseExited

    private void jPanelCheckOutNMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelCheckOutNMouseClicked
    {//GEN-HEADEREND:event_jPanelCheckOutNMouseClicked
        if (jButtonNewOrder.isEnabled() || jButtonNewDeliveryOrder.isEnabled())
        {
            jPanelCheckOut.setVisible(true);

            jPanelDelivery.setVisible(false);
            jPanelReport.setVisible(false);
            jPanelSuppliers.setVisible(false);

            jPanelCheckOut1.setVisible(false);
            jPanelDelivery1.setVisible(false);

            jPanelDeliveryN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelCheckOutN.setBackground(new java.awt.Color(66, 131, 221));
            jPanelSuppliersN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelReportN.setBackground(new java.awt.Color(51, 51, 51));
        }
    }//GEN-LAST:event_jPanelCheckOutNMouseClicked

    private void jPanelSuppliersNMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelSuppliersNMouseClicked
    {//GEN-HEADEREND:event_jPanelSuppliersNMouseClicked

        if (jButtonNewOrder.isEnabled() || jButtonNewDeliveryOrder.isEnabled())
        {
            jPanelSuppliers.setVisible(true);

            jPanelCheckOut.setVisible(false);
            jPanelDelivery.setVisible(false);
            jPanelReport.setVisible(false);

            jPanelCheckOut1.setVisible(false);
            jPanelDelivery1.setVisible(false);

            jPanelDeliveryN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelCheckOutN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelSuppliersN.setBackground(new java.awt.Color(66, 131, 221));
            jPanelReportN.setBackground(new java.awt.Color(51, 51, 51));
        }
    }//GEN-LAST:event_jPanelSuppliersNMouseClicked

    private void jPanelReportNMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanelReportNMouseClicked
    {//GEN-HEADEREND:event_jPanelReportNMouseClicked
        if (jButtonNewOrder.isEnabled() || jButtonNewDeliveryOrder.isEnabled())
        {
            jPanelReport.setVisible(true);

            jPanelCheckOut.setVisible(false);
            jPanelDelivery.setVisible(false);
            jPanelSuppliers.setVisible(false);

            jPanelCheckOut1.setVisible(false);
            jPanelDelivery1.setVisible(false);

            jPanelDeliveryN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelCheckOutN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelSuppliersN.setBackground(new java.awt.Color(51, 51, 51));
            jPanelReportN.setBackground(new java.awt.Color(66, 131, 221));
        }
    }//GEN-LAST:event_jPanelReportNMouseClicked

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemExitActionPerformed
    {//GEN-HEADEREND:event_jMenuItemExitActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Do you want to exit the program?", "Fruit Factory Smoothies & Shakes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemManageEmployeesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemManageEmployeesActionPerformed
    {//GEN-HEADEREND:event_jMenuItemManageEmployeesActionPerformed
        ManageEmployeesGUI manageEmployeesGUI = new ManageEmployeesGUI();

        manageEmployeesGUI.setVisible(true);
    }//GEN-LAST:event_jMenuItemManageEmployeesActionPerformed

    private void jMenuItemManageItemsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemManageItemsActionPerformed
    {//GEN-HEADEREND:event_jMenuItemManageItemsActionPerformed
        ManageItemsGUI manageRecipesGUI = new ManageItemsGUI();

        manageRecipesGUI.setVisible(true);
    }//GEN-LAST:event_jMenuItemManageItemsActionPerformed

    private void jMenuItemManageIngredientsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemManageIngredientsActionPerformed
    {//GEN-HEADEREND:event_jMenuItemManageIngredientsActionPerformed
        ManageIngredientsUI manageIngredientsGUI = new ManageIngredientsUI();

        manageIngredientsGUI.setVisible(true);
    }//GEN-LAST:event_jMenuItemManageIngredientsActionPerformed

    private void jButtonNewDeliveryOrderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNewDeliveryOrderActionPerformed
    {//GEN-HEADEREND:event_jButtonNewDeliveryOrderActionPerformed
        setEnabledAll(jPanelSidePanel, false);

        setEnabledAll(jMenuBar1, false);

        jPanelDelivery1.setVisible(true);

        jPanelCheckOut.setVisible(false);
        jPanelDelivery.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        buttonEnabler(false);

        setTextFieldDeliveryFirstPart();

        itemCount = 1;

        total = 0;

        try
        {
            String query = "SELECT COUNT(*) AS Count FROM Order123456789";

            conn.get(query);

            int count = 0;

            while (conn.rs.next())
            {

                count = Integer.parseInt(conn.rs.getString("Count"));
            }

            int maxID = 0;

            query = "SELECT MAX(OrderID) AS 'Max' FROM Order123456789";

            if (count != 0)
            {

                conn.get(query);

                while (conn.rs.next())
                {
                    maxID = Integer.parseInt(conn.rs.getString("Max"));
                }
            }

            System.out.println(maxID);

            if (count == 0)
            {
                orderID = 10000;
            } else
            {
                orderID = maxID + 50;
            }

        } catch (Exception e)
        {
            System.out.println("Exception");

        }
    }//GEN-LAST:event_jButtonNewDeliveryOrderActionPerformed

    private void jButtonNewOrderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNewOrderActionPerformed
    {//GEN-HEADEREND:event_jButtonNewOrderActionPerformed

        setEnabledAll(jPanelSidePanel, false);

        setEnabledAll(jMenuBar1, false);

        jPanelCheckOut1.setVisible(true);

        jPanelCheckOut.setVisible(false);
        jPanelDelivery.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        jPanelDelivery1.setVisible(false);

        buttonEnabler(false);

        setTextFieldFirstPart();

        itemCount = 1;

        total = 0;

        try
        {
            String query = "SELECT COUNT(*) AS Count FROM Order123456789";

            conn.get(query);

            int count = 0;

            while (conn.rs.next())
            {

                count = Integer.parseInt(conn.rs.getString("Count"));
            }

            int maxID = 0;

            query = "SELECT MAX(OrderID) AS 'Max' FROM Order123456789";

            if (count != 0)
            {

                conn.get(query);

                while (conn.rs.next())
                {
                    maxID = Integer.parseInt(conn.rs.getString("Max"));
                }
            }

            System.out.println(maxID);

            if (count == 0)
            {
                orderID = 10000;
            } else
            {
                orderID = maxID + 50;
            }

        } catch (Exception e)
        {
            System.out.println("Exception");

        }
    }//GEN-LAST:event_jButtonNewOrderActionPerformed

    private void jButtonKeyPad0ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad0ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad0ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "0";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad0ActionPerformed

    private void jButtonKeyPad1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad1ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad1ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "1";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad1ActionPerformed

    private void jButtonKeyPad2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad2ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad2ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "2";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad2ActionPerformed

    private void jButtonKeyPad3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad3ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad3ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "3";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad3ActionPerformed

    private void jButtonKeyPad4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad4ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad4ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "4";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad4ActionPerformed

    private void jButtonKeyPad5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad5ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad5ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "5";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad5ActionPerformed

    private void jButtonKeyPad6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad6ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad6ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "6";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad6ActionPerformed

    private void jButtonKeyPad7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad7ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad7ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "7";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad7ActionPerformed

    private void jButtonKeyPad8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad8ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad8ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "8";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad8ActionPerformed

    private void jButtonKeyPad9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPad9ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPad9ActionPerformed
        String text = jLabelKeyPadDisplay.getText() + "9";

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPad9ActionPerformed

    private void jButtonKeyPadDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPadDeleteActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPadDeleteActionPerformed
        String text = jLabelKeyPadDisplay.getText();

        text = text.substring(0, text.length() - 1);

        jLabelKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonKeyPadDeleteActionPerformed

    private void jButtonKeyPadACActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPadACActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPadACActionPerformed
        jLabelKeyPadDisplay.setText("");
    }//GEN-LAST:event_jButtonKeyPadACActionPerformed

    private void jButtonKeyPadADDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPadADDActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPadADDActionPerformed

        try
        {

            String ItemName = jTableItemList.getValueAt(jTableItemList.getSelectedRow(), 0).toString();

            String query = "SELECT RecipeID FROM RecipeNames123456789 WHERE Name='" + ItemName + "'";

            conn.get(query);

            int itemID = 0;

            while (conn.rs.next())
            {
                itemID = Integer.parseInt(conn.rs.getString("RecipeID"));
            }

            int ItemQuantity = Integer.parseInt(jLabelKeyPadDisplay.getText());

            query = "INSERT INTO Order123456789 VALUES (" + orderID + "," + itemID + "," + ItemQuantity + ")";

            conn.insert(query);

            query = "SELECT UnitPrice  FROM RecipeNames123456789 WHERE RecipeID =" + itemID;

            conn.get(query);

            int price = 0;

            while (conn.rs.next())
            {
                price = Integer.parseInt(conn.rs.getString("UnitPrice"));
            }

            System.out.println(ItemQuantity);

            System.out.println(price);

            total = total + (ItemQuantity * price);

            String textField = "\n" + itemCount + ".              " + ItemName + "\n                 " + itemID + "\t                 " + ItemQuantity + "\t            " + price + "\t          " + (ItemQuantity * price);

            jTextAreaJPanelCheckOut.append(textField);

            String Total = total + ".00";

            jLabeljPanelCheckOutTotal.setText(Total);

            itemCount++;

            System.out.println(total);

        } catch (Exception e)
        {
            System.out.println("Exception");

        }

        jDialogKeyPad.setVisible(false);


    }//GEN-LAST:event_jButtonKeyPadADDActionPerformed

    private void jTableItemListMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableItemListMouseClicked
    {//GEN-HEADEREND:event_jTableItemListMouseClicked
        jDialogKeyPad.setVisible(true);

        jLabelKeyPadDisplay.setText("");
    }//GEN-LAST:event_jTableItemListMouseClicked

    private void jButtonjPanelChechOutDoneActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelChechOutDoneActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelChechOutDoneActionPerformed
        if (itemCount > 1)
        {
            jPanelCheckOut2.setVisible(true);

            setTextFieldSecondPart();

            jTextAreaJPanelPrint.setText(jTextAreaJPanelCheckOut.getText());

            jPanelCheckOut1.setVisible(false);
        }


    }//GEN-LAST:event_jButtonjPanelChechOutDoneActionPerformed

    private void jButtonjPanelChechOutCancleActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelChechOutCancleActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelChechOutCancleActionPerformed
        setEnabledAll(jPanelSidePanel, true);

        setEnabledAll(jMenuBar1, true);

        jPanelCheckOut.setVisible(true);

        jPanelDelivery.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        jPanelCheckOut1.setVisible(false);
        jPanelDelivery1.setVisible(false);

        buttonEnabler(true);

        String query = "DELETE FROM Order123456789 WHERE OrderID=" + orderID;

        conn.insert(query);

        orderID -= 50;
    }//GEN-LAST:event_jButtonjPanelChechOutCancleActionPerformed

    private void jButtonjPanelChechOutPrintActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelChechOutPrintActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelChechOutPrintActionPerformed
        try
        {
            boolean complete = jTextAreaJPanelPrint.print();

            setEnabledAll(jPanelSidePanel, true);

            setEnabledAll(jMenuBar1, true);

            jPanelCheckOut.setVisible(true);

            jPanelDelivery.setVisible(false);
            jPanelReport.setVisible(false);
            jPanelSuppliers.setVisible(false);

            jPanelCheckOut1.setVisible(false);
            jPanelCheckOut2.setVisible(false);
            jPanelDelivery1.setVisible(false);

            buttonEnabler(true);
        } catch (Exception e)
        {

        }
    }//GEN-LAST:event_jButtonjPanelChechOutPrintActionPerformed

    private void jButtonjPanelChechOutCancle1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelChechOutCancle1ActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelChechOutCancle1ActionPerformed
        setEnabledAll(jPanelSidePanel, true);

        setEnabledAll(jMenuBar1, true);

        jPanelCheckOut.setVisible(true);

        jPanelDelivery.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        jPanelCheckOut1.setVisible(false);
        jPanelCheckOut2.setVisible(false);
        jPanelDelivery1.setVisible(false);

        buttonEnabler(true);

        String query = "DELETE FROM Order123456789 WHERE OrderID=" + orderID;

        conn.insert(query);

        orderID -= 50;
    }//GEN-LAST:event_jButtonjPanelChechOutCancle1ActionPerformed

    private void jTableItemListDeliveryMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableItemListDeliveryMouseClicked
    {//GEN-HEADEREND:event_jTableItemListDeliveryMouseClicked
        jDialogDeliveryKeyPad.setVisible(true);

        jLabelDeliveryKeyPadDisplay.setText("");
    }//GEN-LAST:event_jTableItemListDeliveryMouseClicked

    private void jButtonjPanelDeliveryDoneActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelDeliveryDoneActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelDeliveryDoneActionPerformed
        if (itemCount > 1)
        {
            jPanelDelivery2.setVisible(true);

            jTextAreaJPanelDeliveryCustomer.setText(jTextAreaJPanelDelivery.getText());

            jPanelDelivery1.setVisible(false);
        }
    }//GEN-LAST:event_jButtonjPanelDeliveryDoneActionPerformed

    private void jButtonjPanelDeliveryCancleActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelDeliveryCancleActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelDeliveryCancleActionPerformed
        setEnabledAll(jPanelSidePanel, true);

        setEnabledAll(jMenuBar1, true);

        jPanelDelivery.setVisible(true);

        jPanelCheckOut.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        jPanelDelivery1.setVisible(false);

        buttonEnabler(true);

        String query = "DELETE FROM Order123456789 WHERE OrderID=" + orderID;

        conn.insert(query);

        orderID -= 50;
    }//GEN-LAST:event_jButtonjPanelDeliveryCancleActionPerformed

    private void jButtonjPanelDeliveryPrintActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelDeliveryPrintActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelDeliveryPrintActionPerformed
        try
        {
            boolean complete = jTextAreaJPanelPrint.print();

            setEnabledAll(jPanelSidePanel, true);

            setEnabledAll(jMenuBar1, true);

            jPanelDelivery.setVisible(true);

            jPanelCheckOut.setVisible(false);
            jPanelReport.setVisible(false);
            jPanelSuppliers.setVisible(false);

            jPanelDelivery3.setVisible(false);

            buttonEnabler(true);

        } catch (Exception e)
        {

        }
    }//GEN-LAST:event_jButtonjPanelDeliveryPrintActionPerformed

    private void jButtonjPanelDeliveryCancel2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelDeliveryCancel2ActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelDeliveryCancel2ActionPerformed
        setEnabledAll(jPanelSidePanel, true);

        setEnabledAll(jMenuBar1, true);

        jPanelDelivery.setVisible(true);

        jPanelCheckOut.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        jPanelDelivery3.setVisible(false);

        buttonEnabler(true);

        String query = "DELETE FROM Order123456789 WHERE OrderID=" + orderID;

        conn.insert(query);

        int cusID = Integer.parseInt(jTextFieldDeliveryCusID.getText());

        query = "DELETE FROM Customers123456789 WHERE CustomerID =" + cusID;

        conn.insert(query);

        orderID -= 50;
    }//GEN-LAST:event_jButtonjPanelDeliveryCancel2ActionPerformed

    private void jButtonjPanelDeliveryCustomerNextActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelDeliveryCustomerNextActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelDeliveryCustomerNextActionPerformed
        jPanelDelivery3.setVisible(true);

        jPanelDelivery2.setVisible(false);

        jTextAreaJPanelDeliveryPrint.setText(jTextAreaJPanelDelivery.getText());

        int cusID = Integer.parseInt(jTextFieldDeliveryCusID.getText());
        String cusName = jTextFieldDeliveryCusName.getText();
        String cusAddress = jTextFieldDeliveryCusAddress.getText();
        String cusPhone = jTextFieldDeliveryCusPhone.getText();

        String query = "INSERT INTO Customers123456789 "
                + "VALUES(" + cusID + ",'" + cusName + "','" + cusAddress + "','" + cusPhone + "')";

        conn.insert(query);

        setTextFieldDeliverySecondPart();
    }//GEN-LAST:event_jButtonjPanelDeliveryCustomerNextActionPerformed

    private void jButtonjPanelDeliveryCancle1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonjPanelDeliveryCancle1ActionPerformed
    {//GEN-HEADEREND:event_jButtonjPanelDeliveryCancle1ActionPerformed
        setEnabledAll(jPanelSidePanel, true);

        setEnabledAll(jMenuBar1, true);

        jPanelDelivery.setVisible(true);

        jPanelCheckOut.setVisible(false);
        jPanelReport.setVisible(false);
        jPanelSuppliers.setVisible(false);

        jPanelDelivery2.setVisible(false);

        buttonEnabler(true);

        String query = "DELETE FROM Order123456789 WHERE OrderID=" + orderID;

        conn.insert(query);

        orderID -= 50;
    }//GEN-LAST:event_jButtonjPanelDeliveryCancle1ActionPerformed

    private void jButtonDeliveryKeyPadDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPadDeleteActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPadDeleteActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText();

        text = text.substring(0, text.length() - 1);

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPadDeleteActionPerformed

    private void jButtonDeliveryKeyPad0ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad0ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad0ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "0";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad0ActionPerformed

    private void jButtonDeliveryKeyPadACActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPadACActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPadACActionPerformed
        jLabelKeyPadDisplay.setText("");
    }//GEN-LAST:event_jButtonDeliveryKeyPadACActionPerformed

    private void jButtonDeliveryKeyPad1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad1ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad1ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "1";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad1ActionPerformed

    private void jButtonDeliveryKeyPad2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad2ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad2ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "2";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad2ActionPerformed

    private void jButtonDeliveryKeyPad3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad3ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad3ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "3";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad3ActionPerformed

    private void jButtonDeliveryKeyPad6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad6ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad6ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "6";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad6ActionPerformed

    private void jButtonDeliveryKeyPad4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad4ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad4ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "4";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad4ActionPerformed

    private void jButtonDeliveryKeyPad5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad5ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad5ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "5";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad5ActionPerformed

    private void jButtonDeliveryKeyPad9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad9ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad9ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "9";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad9ActionPerformed

    private void jButtonDeliveryKeyPad8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad8ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad8ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "8";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad8ActionPerformed

    private void jButtonDeliveryKeyPad7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeliveryKeyPad7ActionPerformed
    {//GEN-HEADEREND:event_jButtonDeliveryKeyPad7ActionPerformed
        String text = jLabelDeliveryKeyPadDisplay.getText() + "7";

        jLabelDeliveryKeyPadDisplay.setText(text);
    }//GEN-LAST:event_jButtonDeliveryKeyPad7ActionPerformed

    private void jButtonKeyPadADD1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonKeyPadADD1ActionPerformed
    {//GEN-HEADEREND:event_jButtonKeyPadADD1ActionPerformed
        try
        {

            String ItemName = jTableItemListDelivery.getValueAt(jTableItemListDelivery.getSelectedRow(), 0).toString();

            String query = "SELECT RecipeID FROM RecipeNames123456789 WHERE Name='" + ItemName + "'";

            conn.get(query);

            int itemID = 0;

            while (conn.rs.next())
            {
                itemID = Integer.parseInt(conn.rs.getString("RecipeID"));
            }

            int ItemQuantity = Integer.parseInt(jLabelDeliveryKeyPadDisplay.getText());

            query = "INSERT INTO Order123456789 VALUES (" + orderID + "," + itemID + "," + ItemQuantity + ")";

            conn.insert(query);

            query = "SELECT UnitPrice  FROM RecipeNames123456789 WHERE RecipeID =" + itemID;

            conn.get(query);

            int price = 0;

            while (conn.rs.next())
            {
                price = Integer.parseInt(conn.rs.getString("UnitPrice"));
            }

            System.out.println(ItemQuantity);

            System.out.println(price);

            total = total + (ItemQuantity * price);

            String textField = "\n" + itemCount + ".              " + ItemName + "\n                 " + itemID + "\t                 " + ItemQuantity + "\t            " + price + "\t          " + (ItemQuantity * price);

            jTextAreaJPanelDelivery.append(textField);

            String Total = total + ".00";

            jLabeljPanelDeliveryTotal.setText(Total);

            itemCount++;

            System.out.println(total);

        } catch (Exception e)
        {
            System.out.println("Exception");

        }

        jDialogDeliveryKeyPad.setVisible(false);
    }//GEN-LAST:event_jButtonKeyPadADD1ActionPerformed

    private void jTextFieldDeliveryCusIDKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldDeliveryCusIDKeyTyped
    {//GEN-HEADEREND:event_jTextFieldDeliveryCusIDKeyTyped
        char key = evt.getKeyChar();

        if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACKSPACE || key == KeyEvent.VK_DELETE)
        {
            evt.consume();
        }

        if (jTextFieldDeliveryCusID.getText().length() >= 6)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDeliveryCusIDKeyTyped

    private void jTextFieldDeliveryCusNameKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldDeliveryCusNameKeyTyped
    {//GEN-HEADEREND:event_jTextFieldDeliveryCusNameKeyTyped
        char key = evt.getKeyChar();

        if (!(Character.isLetter(key)) || key == KeyEvent.VK_BACKSPACE || key == KeyEvent.VK_DELETE)
        {
            evt.consume();
        }

        if (jTextFieldDeliveryCusName.getText().length() >= 20)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDeliveryCusNameKeyTyped

    private void jTextFieldDeliveryCusAddressKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldDeliveryCusAddressKeyTyped
    {//GEN-HEADEREND:event_jTextFieldDeliveryCusAddressKeyTyped
        char key = evt.getKeyChar();

        if (!(Character.isLetterOrDigit(key)) || key == KeyEvent.VK_BACKSPACE || key == KeyEvent.VK_DELETE)
        {
            evt.consume();
        }

        if (jTextFieldDeliveryCusAddress.getText().length() >= 100)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDeliveryCusAddressKeyTyped

    private void jTextFieldDeliveryCusPhoneKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldDeliveryCusPhoneKeyTyped
    {//GEN-HEADEREND:event_jTextFieldDeliveryCusPhoneKeyTyped
        char key = evt.getKeyChar();

        if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACKSPACE || key == KeyEvent.VK_DELETE)
        {
            evt.consume();
        }

        if (jTextFieldDeliveryCusPhone.getText().length() >= 10)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDeliveryCusPhoneKeyTyped

    private void jButtonManageSuppliersActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonManageSuppliersActionPerformed
    {//GEN-HEADEREND:event_jButtonManageSuppliersActionPerformed
        ManageSuppliersGUI manageSuppliersGUI = new ManageSuppliersGUI();

        manageSuppliersGUI.setVisible(true);
    }//GEN-LAST:event_jButtonManageSuppliersActionPerformed

    private void jTableIReportMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableIReportMouseClicked
    {//GEN-HEADEREND:event_jTableIReportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableIReportMouseClicked

    private void jMenuItemLogOutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemLogOutActionPerformed
    {//GEN-HEADEREND:event_jMenuItemLogOutActionPerformed
        Login login = new Login();

        login.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jMenuItemLogOutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeliveryKeyPad0;
    private javax.swing.JButton jButtonDeliveryKeyPad1;
    private javax.swing.JButton jButtonDeliveryKeyPad2;
    private javax.swing.JButton jButtonDeliveryKeyPad3;
    private javax.swing.JButton jButtonDeliveryKeyPad4;
    private javax.swing.JButton jButtonDeliveryKeyPad5;
    private javax.swing.JButton jButtonDeliveryKeyPad6;
    private javax.swing.JButton jButtonDeliveryKeyPad7;
    private javax.swing.JButton jButtonDeliveryKeyPad8;
    private javax.swing.JButton jButtonDeliveryKeyPad9;
    private javax.swing.JButton jButtonDeliveryKeyPadAC;
    private javax.swing.JButton jButtonDeliveryKeyPadDelete;
    private javax.swing.JButton jButtonKeyPad0;
    private javax.swing.JButton jButtonKeyPad1;
    private javax.swing.JButton jButtonKeyPad2;
    private javax.swing.JButton jButtonKeyPad3;
    private javax.swing.JButton jButtonKeyPad4;
    private javax.swing.JButton jButtonKeyPad5;
    private javax.swing.JButton jButtonKeyPad6;
    private javax.swing.JButton jButtonKeyPad7;
    private javax.swing.JButton jButtonKeyPad8;
    private javax.swing.JButton jButtonKeyPad9;
    private javax.swing.JButton jButtonKeyPadAC;
    private javax.swing.JButton jButtonKeyPadADD;
    private javax.swing.JButton jButtonKeyPadADD1;
    private javax.swing.JButton jButtonKeyPadDelete;
    private javax.swing.JButton jButtonManageSuppliers;
    private javax.swing.JButton jButtonNewDeliveryOrder;
    private javax.swing.JButton jButtonNewOrder;
    private javax.swing.JButton jButtonjPanelChechOutCancle;
    private javax.swing.JButton jButtonjPanelChechOutCancle1;
    private javax.swing.JButton jButtonjPanelChechOutDone;
    private javax.swing.JButton jButtonjPanelChechOutPrint;
    private javax.swing.JButton jButtonjPanelDeliveryCancel2;
    private javax.swing.JButton jButtonjPanelDeliveryCancle;
    private javax.swing.JButton jButtonjPanelDeliveryCancle1;
    private javax.swing.JButton jButtonjPanelDeliveryCustomerNext;
    private javax.swing.JButton jButtonjPanelDeliveryDone;
    private javax.swing.JButton jButtonjPanelDeliveryPrint;
    private javax.swing.JDialog jDialogDeliveryKeyPad;
    private javax.swing.JDialog jDialogKeyPad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDeliveryKeyPadDisplay;
    private javax.swing.JLabel jLabelKeyPadDisplay;
    private javax.swing.JLabel jLabeljPanelCheckOutTotal;
    private javax.swing.JLabel jLabeljPanelDeliveryTotal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemLogOut;
    private javax.swing.JMenuItem jMenuItemManageEmployees;
    private javax.swing.JMenuItem jMenuItemManageIngredients;
    private javax.swing.JMenuItem jMenuItemManageItems;
    private javax.swing.JPanel jPanelCheckOut;
    private javax.swing.JPanel jPanelCheckOut1;
    private javax.swing.JPanel jPanelCheckOut2;
    private javax.swing.JPanel jPanelCheckOutN;
    private javax.swing.JPanel jPanelDelivery;
    private javax.swing.JPanel jPanelDelivery1;
    private javax.swing.JPanel jPanelDelivery2;
    private javax.swing.JPanel jPanelDelivery3;
    private javax.swing.JPanel jPanelDeliveryN;
    private javax.swing.JPanel jPanelReport;
    private javax.swing.JPanel jPanelReportN;
    private javax.swing.JPanel jPanelSidePanel;
    private javax.swing.JPanel jPanelSuppliers;
    private javax.swing.JPanel jPanelSuppliersN;
    private javax.swing.JPanel jPanelTopPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTableIReport;
    private javax.swing.JTable jTableItemList;
    private javax.swing.JTable jTableItemListDelivery;
    private javax.swing.JTextArea jTextAreaJPanelCheckOut;
    private javax.swing.JTextArea jTextAreaJPanelDelivery;
    private javax.swing.JTextArea jTextAreaJPanelDeliveryCustomer;
    private javax.swing.JTextArea jTextAreaJPanelDeliveryPrint;
    private javax.swing.JTextArea jTextAreaJPanelPrint;
    private javax.swing.JTextField jTextFieldDeliveryCusAddress;
    private javax.swing.JTextField jTextFieldDeliveryCusID;
    private javax.swing.JTextField jTextFieldDeliveryCusName;
    private javax.swing.JTextField jTextFieldDeliveryCusPhone;
    // End of variables declaration//GEN-END:variables
}
