package GUI;

import DatabaseLayer.DatabaseConnection;
import com.sun.glass.events.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class ManageItemsGUI extends javax.swing.JFrame
{

    DatabaseConnection conn;

    public ManageItemsGUI()
    {
        initComponents();

        conn = DatabaseConnection.getSingleConnection();
        
        //Call jComboBoxSelectItemFill() method to populate jComboBoxSelectItem Combo box.

        jComboBoxSelectItemFill();
        
        //Call jComboBoxSelectItemFill() method to populate jComboBoxSelectIngredient Combo box.

        jComboBoxSelectIngredientFill();
        
        //Call displayTable() method to populate jTableManageIngredients table.

        displayTable();

    }

    //Method to populate jComboBoxSelectItem Combo box.
    
    void jComboBoxSelectItemFill()
    {
        try
        {
            conn.get("SELECT * FROM RecipeNames123456789");

            while (conn.rs.next())
            {
                String name = conn.rs.getString("Name");

                jComboBoxSelectItem.addItem(name);
            }
        } catch (Exception e)
        {
        }
    }

    //method to empty jComboBoxSelectItem Combo box.
    
    void jComboBoxSelectItemEmpty()
    {
        jComboBoxSelectItem.removeAllItems();
    }

    //Method to populate jComboBoxSelectIngredient Combo box.
    
    void jComboBoxSelectIngredientFill()
    {
        try
        {
            conn.get("SELECT * FROM Ingredients123456789");

            while (conn.rs.next())
            {
                String name = conn.rs.getString("Name");

                jComboBoxSelectIngredient.addItem(name);
            }
        } catch (Exception e)
        {
        }
    }

    //Method to empty jComboBoxSelectIngredient Combo box.
    
    void jComboBoxSelectIngredientEmpty()
    {
        jComboBoxSelectIngredient.removeAllItems();
    }

    //Method to populate jTableManageIngredients table.
    
    void displayTable()
    {
        try
        {
            String item = jComboBoxSelectItem.getSelectedItem().toString();

            item = "SELECT i2.Name,i1.Quantity "
                    + "FROM Item123456789 i1,Ingredients123456789 i2,RecipeNames123456789 r "
                    + "WHERE i1.RecipeID=r.RecipeID AND i1.IngredientID=i2.IngredientID AND r.Name='" + item + "'";

            conn.get(item);

            while (conn.rs.next())
            {
                String Name = conn.rs.getString(1);
                String Quantity = conn.rs.getString(2);

                Object[] content =
                {
                    Name, Quantity
                };

                DefaultTableModel model = (DefaultTableModel) jTableManageIngredients.getModel();

                model.addRow(content);
            }
        } catch (Exception e)
        {
        }

    }

    //Method to emptying out jTableManageIngredients table.
    
    void emptyTable()
    {
        DefaultTableModel model = (DefaultTableModel) jTableManageIngredients.getModel();

        int count = model.getRowCount();

        for (int i = 0; i < count; i++)
        {
            model.removeRow(0);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDialogAddIngredient = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonCloseAddIngredient = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxSelectIngredient = new javax.swing.JComboBox<>();
        jSpinnerQuantity = new javax.swing.JSpinner();
        jDialogAddItem = new javax.swing.JDialog();
        jButtonAddtem = new javax.swing.JButton();
        jButtonCloseAddItem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldItemID = new javax.swing.JTextField();
        jTextFieldItemName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldItemPrice = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableManageIngredients = new javax.swing.JTable();
        jButtonDeleteIngredient = new javax.swing.JButton();
        jButtonAddIngredient = new javax.swing.JButton();
        jComboBoxSelectItem = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButtonDeleteItem = new javax.swing.JButton();
        jButtonAddNewItem = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jDialogAddIngredient.setTitle("Add New Ingredient");
        jDialogAddIngredient.setLocationByPlatform(true);
        jDialogAddIngredient.setMinimumSize(new java.awt.Dimension(500, 300));
        jDialogAddIngredient.setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Quantity");

        jButtonAdd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonCloseAddIngredient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonCloseAddIngredient.setText("Close");
        jButtonCloseAddIngredient.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCloseAddIngredientActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Select Ingredient");

        jComboBoxSelectIngredient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jSpinnerQuantity.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jSpinnerQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        javax.swing.GroupLayout jDialogAddIngredientLayout = new javax.swing.GroupLayout(jDialogAddIngredient.getContentPane());
        jDialogAddIngredient.getContentPane().setLayout(jDialogAddIngredientLayout);
        jDialogAddIngredientLayout.setHorizontalGroup(
            jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddIngredientLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxSelectIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAddIngredientLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdd)
                    .addGroup(jDialogAddIngredientLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jButtonCloseAddIngredient)))
                .addGap(129, 129, 129))
        );
        jDialogAddIngredientLayout.setVerticalGroup(
            jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddIngredientLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxSelectIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinnerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jDialogAddIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCloseAddIngredient)
                    .addComponent(jButtonAdd))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jDialogAddItem.setTitle("Add New Item");
        jDialogAddItem.setLocationByPlatform(true);
        jDialogAddItem.setMinimumSize(new java.awt.Dimension(400, 400));
        jDialogAddItem.setResizable(false);

        jButtonAddtem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonAddtem.setText("Add");
        jButtonAddtem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddtemActionPerformed(evt);
            }
        });

        jButtonCloseAddItem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonCloseAddItem.setText("Close");
        jButtonCloseAddItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCloseAddItemActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("RecipeID");

        jTextFieldItemID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextFieldItemID.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                jTextFieldItemIDKeyTyped(evt);
            }
        });

        jTextFieldItemName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextFieldItemName.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                jTextFieldItemNameKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Name ");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Item Price");

        jTextFieldItemPrice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextFieldItemPrice.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                jTextFieldItemPriceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jDialogAddItemLayout = new javax.swing.GroupLayout(jDialogAddItem.getContentPane());
        jDialogAddItem.getContentPane().setLayout(jDialogAddItemLayout);
        jDialogAddItemLayout.setHorizontalGroup(
            jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddItemLayout.createSequentialGroup()
                .addGroup(jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAddItemLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldItemPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialogAddItemLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jButtonAddtem)
                        .addGap(86, 86, 86)
                        .addComponent(jButtonCloseAddItem)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jDialogAddItemLayout.setVerticalGroup(
            jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddItemLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldItemPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(83, 83, 83)
                .addGroup(jDialogAddItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddtem)
                    .addComponent(jButtonCloseAddItem))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Items");
        setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit Item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jTableManageIngredients.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTableManageIngredients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Ingredient Name", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(jTableManageIngredients);

        jButtonDeleteIngredient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDeleteIngredient.setText("Delete Selected Ingredient");
        jButtonDeleteIngredient.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonDeleteIngredientActionPerformed(evt);
            }
        });

        jButtonAddIngredient.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonAddIngredient.setText("Add New Ingredient");
        jButtonAddIngredient.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddIngredientActionPerformed(evt);
            }
        });

        jComboBoxSelectItem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxSelectItem.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jComboBoxSelectItemMouseEntered(evt);
            }
        });
        jComboBoxSelectItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxSelectItemActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Select an Item : ");

        jButtonDeleteItem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDeleteItem.setText("Delete This Item");
        jButtonDeleteItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonDeleteItemActionPerformed(evt);
            }
        });

        jButtonAddNewItem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonAddNewItem.setText("Add New Item");
        jButtonAddNewItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAddNewItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAddIngredient)
                        .addGap(29, 29, 29)
                        .addComponent(jButtonDeleteIngredient))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBoxSelectItem, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(jButtonDeleteItem)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAddNewItem))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSelectItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButtonDeleteItem)
                    .addComponent(jButtonAddNewItem))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeleteIngredient)
                    .addComponent(jButtonAddIngredient))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jButtonClose.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonClose)
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jButtonClose)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeleteIngredientActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteIngredientActionPerformed
    {//GEN-HEADEREND:event_jButtonDeleteIngredientActionPerformed

        //Delete an item from Item123456789 table.
        
        DefaultTableModel model = (DefaultTableModel) jTableManageIngredients.getModel();

        if (jTableManageIngredients.getSelectedRow() == -1)
        {
            if (jTableManageIngredients.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(rootPane, "No Data to Delete.", "Edit Employee", JOptionPane.OK_OPTION);
            } else
            {
                JOptionPane.showMessageDialog(rootPane, "Select a Row to Delete.", "Edit Employee", JOptionPane.OK_OPTION);
            }
        } else
        {
            String item = jComboBoxSelectItem.getSelectedItem().toString();

            String IngredientName = jTableManageIngredients.getValueAt(jTableManageIngredients.getSelectedRow(), 0).toString();

            String query = "DELETE FROM Item123456789 WHERE RecipeID=(SELECT RecipeID FROM RecipeNames123456789 WHERE Name='" + item + "') AND  IngredientID=(SELECT IngredientID FROM Ingredients123456789 WHERE Name='" + IngredientName + "')";

            boolean status = conn.deleteRow(query);

            if (status)
            {
                JOptionPane.showMessageDialog(rootPane, "Successfully Deleted.", "Edit Ingredien", JOptionPane.PLAIN_MESSAGE);
                model.removeRow(jTableManageIngredients.getSelectedRow());
            }
        }
    }//GEN-LAST:event_jButtonDeleteIngredientActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCloseActionPerformed
    {//GEN-HEADEREND:event_jButtonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonAddIngredientActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddIngredientActionPerformed
    {//GEN-HEADEREND:event_jButtonAddIngredientActionPerformed
        jDialogAddIngredient.setVisible(true);
    }//GEN-LAST:event_jButtonAddIngredientActionPerformed

    private void jButtonCloseAddIngredientActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCloseAddIngredientActionPerformed
    {//GEN-HEADEREND:event_jButtonCloseAddIngredientActionPerformed
        jDialogAddIngredient.setVisible(false);
    }//GEN-LAST:event_jButtonCloseAddIngredientActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddActionPerformed
    {//GEN-HEADEREND:event_jButtonAddActionPerformed
        //Adding new Item to the Item123456789 table.
        
        String itemName = jComboBoxSelectItem.getSelectedItem().toString();
        String IngredientName = jComboBoxSelectIngredient.getSelectedItem().toString();
        int Quantity = Integer.parseInt(jSpinnerQuantity.getValue().toString());

        try
        {

            int RecipeID = 0;

            String query = "SELECT RecipeID FROM RecipeNames123456789 WHERE Name='" + itemName + "'";

            conn.get(query);

            while (conn.rs.next())
            {
                RecipeID = Integer.parseInt(conn.rs.getString("RecipeID"));
            }

            int IngredientID = 0;

            query = "SELECT IngredientID  FROM Ingredients123456789 WHERE Name='" + IngredientName + "'";

            conn.get(query);

            while (conn.rs.next())
            {
                IngredientID = Integer.parseInt(conn.rs.getString("IngredientID"));
            }

            query = "INSERT INTO Item123456789 VALUES ("+RecipeID+","+IngredientID+","+Quantity+")";

            boolean status = conn.insert(query);

            jDialogAddIngredient.setVisible(false);

            emptyTable();
            displayTable();

            if (status)
            {
                JOptionPane.showMessageDialog(rootPane, "Successfully Added.", "Add Ingredient", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception e)
        {
            System.out.println("Exception");
        }

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteItemActionPerformed
    {//GEN-HEADEREND:event_jButtonDeleteItemActionPerformed
        //Delete an item from RecipeNames123456789 table.
        
        String item = jComboBoxSelectItem.getSelectedItem().toString();

        item = "DELETE FROM RecipeNames123456789 WHERE Name='" + item + "'";

        boolean status = conn.deleteRow(item);

        jComboBoxSelectItemEmpty();

        jComboBoxSelectItemFill();

        if (status)
        {
            JOptionPane.showMessageDialog(rootPane, "Successfully Deleted.", "Edit Recipe", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDeleteItemActionPerformed

    private void jButtonAddNewItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddNewItemActionPerformed
    {//GEN-HEADEREND:event_jButtonAddNewItemActionPerformed
        jDialogAddItem.setVisible(true);

        jTextFieldItemID.setText("");
        jTextFieldItemName.setText("");
        jTextFieldItemPrice.setText("");
    }//GEN-LAST:event_jButtonAddNewItemActionPerformed

    private void jButtonAddtemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddtemActionPerformed
    {//GEN-HEADEREND:event_jButtonAddtemActionPerformed

        //Add new item to RecipeNames123456789 table.
        
        int RecipeID = Integer.parseInt(jTextFieldItemID.getText());
        String Itemname = jTextFieldItemName.getText();
        int ItemPrice = Integer.parseInt(jTextFieldItemPrice.getText());

        String query = "INSERT INTO RecipeNames123456789 "
                + "VALUES(" + RecipeID + ",'" + Itemname + "'," + ItemPrice + ")";

        boolean status = conn.insert(query);

        jDialogAddItem.setVisible(false);

        jComboBoxSelectItemEmpty();
        jComboBoxSelectItemFill();

        if (status)
        {
            JOptionPane.showMessageDialog(rootPane, "Successfully Added.", "Add Item", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAddtemActionPerformed

    private void jButtonCloseAddItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCloseAddItemActionPerformed
    {//GEN-HEADEREND:event_jButtonCloseAddItemActionPerformed
        jDialogAddItem.setVisible(false);
    }//GEN-LAST:event_jButtonCloseAddItemActionPerformed

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //Input Validiation.
    
    private void jTextFieldItemIDKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldItemIDKeyTyped
    {//GEN-HEADEREND:event_jTextFieldItemIDKeyTyped
        char key = evt.getKeyChar();

        if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACKSPACE || key == KeyEvent.VK_DELETE)
        {
            evt.consume();
        }

        if (jTextFieldItemID.getText().length() >= 2)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldItemIDKeyTyped

    private void jTextFieldItemNameKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldItemNameKeyTyped
    {//GEN-HEADEREND:event_jTextFieldItemNameKeyTyped
        char key = evt.getKeyChar();

        if (!(Character.isLetter(key)) || key == KeyEvent.VK_BACKSPACE || key == KeyEvent.VK_DELETE)
        {
            evt.consume();
        }

        if (jTextFieldItemName.getText().length() >= 100)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldItemNameKeyTyped

    private void jTextFieldItemPriceKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldItemPriceKeyTyped
    {//GEN-HEADEREND:event_jTextFieldItemPriceKeyTyped
        char key = evt.getKeyChar();

        if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACKSPACE || key == KeyEvent.VK_DELETE)
        {
            evt.consume();
        }

        if (jTextFieldItemPrice.getText().length() >= 4)
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldItemPriceKeyTyped

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    //ItemListner to refresh jTableManageIngredients table when new item selected from jComboBoxSelectItem Combo box.
    
    private void jComboBoxSelectItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxSelectItemActionPerformed
    {//GEN-HEADEREND:event_jComboBoxSelectItemActionPerformed
        jComboBoxSelectItem.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if ((e.getStateChange() == ItemEvent.SELECTED))
                {

                    emptyTable();
                    displayTable();
                }
            }
        });
    }//GEN-LAST:event_jComboBoxSelectItemActionPerformed

    private void jComboBoxSelectItemMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBoxSelectItemMouseEntered
    {//GEN-HEADEREND:event_jComboBoxSelectItemMouseEntered
        
    }//GEN-LAST:event_jComboBoxSelectItemMouseEntered

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
            java.util.logging.Logger.getLogger(ManageItemsGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ManageItemsGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ManageItemsGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ManageItemsGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ManageItemsGUI().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddIngredient;
    private javax.swing.JButton jButtonAddNewItem;
    private javax.swing.JButton jButtonAddtem;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonCloseAddIngredient;
    private javax.swing.JButton jButtonCloseAddItem;
    private javax.swing.JButton jButtonDeleteIngredient;
    private javax.swing.JButton jButtonDeleteItem;
    private javax.swing.JComboBox<String> jComboBoxSelectIngredient;
    private javax.swing.JComboBox<String> jComboBoxSelectItem;
    private javax.swing.JDialog jDialogAddIngredient;
    private javax.swing.JDialog jDialogAddItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerQuantity;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableManageIngredients;
    private javax.swing.JTextField jTextFieldItemID;
    private javax.swing.JTextField jTextFieldItemName;
    private javax.swing.JTextField jTextFieldItemPrice;
    // End of variables declaration//GEN-END:variables

}
