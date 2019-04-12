
package miahorcadov2;

import Entidades.Diccionario;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Eli
 */
public class MiAhorcadoV extends javax.swing.JFrame {

    /**
     * Creates new form MiAhorcadoV
     */
    public ImageIcon imgs[];
    public JButton btns[];
    public String msgs[];
    public int ran;
    public int err;
    public String res[];
    Diccionario lista;
   // Diccionario lista = new Diccionario();
    
    
    public MiAhorcadoV() {
        initComponents();
        imgs = new ImageIcon[6];
        btns = new JButton[27];
        msgs = new String[20];
        String [] pal ;
     //   Diccionario lista = new Diccionario();
       
         
        
  /*       //botones para las letras
        btns[1] = jButton2;
        btns[2] = jButton3;
        btns[3] = jButton4;
        btns[4] = jButton5;
        btns[5] = jButton6;
        btns[6] = jButton7;
        btns[7] = jButton8;
        btns[8] = jButton9;
        btns[9] = jButton10;
        btns[10] = jButton11;
        btns[11] = jButton12;
        btns[12] = jButton13;
        btns[13] = jButton14;
        btns[14] = jButton15;
        btns[15] = jButton16;
        btns[16] = jButton17;
        btns[17] = jButton18;
        btns[18] = jButton19;
        btns[19] = jButton20;
        btns[20] = jButton21;
        btns[21] = jButton22;
        btns[22] = jButton23;
        btns[23] = jButton24;
        btns[24] = jButton25;
        btns[25] = jButton26;
        btns[26] = jButton27;

      */  
        //se asigna un evento a cada letra para comprobar que exista en la palabra a adivinar
     /*   for (int i = 1; i < 27; i++) {
            btns[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    VerificarLetra(e);
                }
            });
        }
        */
     
        iniciar();
        
        
    }
    public void iniciarABC()
    {
    Container cp = this.jpanLetras;
        GridLayout gl = new GridLayout(3, 7);
        gl.setHgap(2);
        gl.setVgap(2);
        cp.setLayout(gl);
        cp.setMaximumSize(new Dimension(530, 200));
        cp.setBackground(new Color(63, 51, 49));
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        //centrar iconos

        for (int i = 0; i < letras.length(); i++)
        {
        JButton[] botones = null;
            botones[i] = new JButton();
            cp.add(botones[i]);
            botones[i].setText(String.valueOf(letras.charAt(i)));
            botones[i].setFont(new Font("Tahoma", 0, 20));
            botones[i].setVisible(true);
      /*      botones[i].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
   
        */
        }
    }
    
     //funcion para comenzar los parametros del juego o iniciar una nueva partida
    public void iniciar() {
        //ERRORES EN 0
        err = 0;
        jButton1.setIcon(imgs[0]);
        jTextPane1.setText("");
        //para activar las letras del tablero
        for (int i = 1; i < 27; i++) {
            btns[i].setEnabled(true);
        }
        //para generar una palabra aleatoriamente
       // ran = (int) 0 + (int) (Math.random() * ((msgs.length - 1) + 1));
        //SEPARA EL MENSAJE POR PALABRAS
        //ACA DEBO PONER EL ARRAY DE LA LISTA DEL DICCIONARIO PERO NO ME SALE
        this.lista = Diccionario.cargarDiccionario("diccionario.xml");
   //     String pal[] = new String[this.lista.getLista().size()];
       
      //  res = new String[msgs[ran].length() + 1];
   //     int j = 0;
        for (int i = 0; i < pal.length; i++)
        {
            pal[i] = this.lista.getLista().get(i).getPalabra();
        }

        ran = (int) 0 + (int) (Math.random() * ((pal.length - 1) + 1));

        res = new String[pal[ran].length()];

        int j = 0;
        
        // seran los guiones que van debajo de las letras como una separacion_
        
        
        for (String pal1 : pal) {
            for (int i = 0; i < pal1.length(); i++) {
                jTextPane1.setText(jTextPane1.getText() + "_ ");
                res[i] = "_";
            }
            jTextPane1.setText(jTextPane1.getText() + "\n");
            res[j++] = " ";
        }
    }

    
         //al presionar una letra, esta se buscara si pertenece a la palabra, de lo contrario la marcara como error 
    public void VerificarLetra(ActionEvent e) {
        
         JButton bt = (JButton) e.getSource();
        char c;
        //la tecla es inicializada
        System.out.println("letra presionda" + bt.getText());
        
        c = bt.getText().charAt(0);
        //busca si la letra esta en la frase
        boolean esta = false;
        for (int j = 0; j < pal[ran].length(); j++)
        {
            if (c == pal[ran].charAt(j))
            {
                res[j] = c + "";
                esta = true;
            }
        }
        //SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
        if (esta)
        {
            jTextPane1.setText("");
            for (String re : res)
            {
                if (" ".equals(re))
                {
                    jTextPane1.setText(jTextPane1.getText() + "\n");
                }
                else
                {
                    jTextPane1.setText(jTextPane1.getText() + re + " ");
                }
            }
            //comprueba que las letras restantes y faltantes, en caso de que ya no haya letras GANA
            boolean gano = true;
            for (String re : res)
            {
                if (re.equals("_"))
                {
                    gano = false;
                    break;
                }
            }
            //al ser correcta se muestra un mensaje y se reinicia el juego
            if (gano)
            {
                JOptionPane.showMessageDialog(this, "Ganaste xD!!!");
                iniciar();
                return;
            }
            //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
        }
        else
        {
            jButton1.setIcon(imgs[++err]);
            //SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAGE DE:
            if (err == 5)
            {
                JOptionPane.showMessageDialog(this, "Proba con otra palabra la respuesta es: \n" + msgs[ran]);
                iniciar();
                return;
            }
        }
        //esta es la linea que desactiva las letras despues de ser usadas :3
        bt.setEnabled(false);

    }
        
        
        
        /*
        JButton bt = (JButton) e.getSource();
        char c[];
        //busca la letra en la palabra despues de haber sido presionada
        for (int i = 1; i < 27; i++) {
            if (bt == btns[i]) {
                //la tecla es inicializada
                c = Character.toChars(64 + i);
                //busca si la letra esta en la frase
                boolean esta = false;
                for (int j = 0; j < msgs[ran].length(); j++) {
                    if (c[0] == msgs[ran].charAt(j)) {
                        res[j] = c[0] + "";
                        esta = true;
                    }
                }
                //SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
                if (esta) {
                    jTextPane1.setText("");
                    for (String re : res) {
                        if (" ".equals(re)) {
                            jTextPane1.setText(jTextPane1.getText() + "\n");
                        } else {
                            jTextPane1.setText(jTextPane1.getText() + re + " ");
                        }
                    }
                    //comprueba que las letras restantes y faltantes, en caso de que ya no haya letras GANA
                    boolean gano = true;
                    for (String re : res) {
                        if (re.equals("_")) {
                            gano = false;
                            break;
                        }
                    }
                    //al ser correcta se muestra un mensaje y se reinicia el juego
                    if (gano) {
                        JOptionPane.showMessageDialog(this, "Ganaste xD!!!");
                        iniciar();
                        return;
                    }
                    //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
                } else {
                    jButton1.setIcon(imgs[++err]);
                    //SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAGE DE:
                    if (err == 5) {
                        JOptionPane.showMessageDialog(this, "Proba con otra palabra la respuesta es: \n" + msgs[ran]);
                        iniciar();
                        return;
                    }
                }
                //esta es la linea que desactiva las letras despues de ser usadas :3
                bt.setEnabled(false);
                break;
            }
        }
*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jpanLetras = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 102, 255));

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));
        jPanel1.setForeground(new java.awt.Color(204, 0, 204));

        jScrollPane1.setViewportView(jTextPane1);

        jButton1.setBackground(new java.awt.Color(255, 255, 204));

        jButton2.setBackground(new java.awt.Color(204, 51, 255));
        jButton2.setText("Nueva Partida");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel1.setText("AHORCADO DE PINKY");

        jButton28.setText("Salir");

        javax.swing.GroupLayout jpanLetrasLayout = new javax.swing.GroupLayout(jpanLetras);
        jpanLetras.setLayout(jpanLetrasLayout);
        jpanLetrasLayout.setHorizontalGroup(
            jpanLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jpanLetrasLayout.setVerticalGroup(
            jpanLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jpanLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jpanLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        iniciar();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiAhorcadoV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiAhorcadoV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiAhorcadoV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiAhorcadoV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiAhorcadoV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton28;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel jpanLetras;
    // End of variables declaration//GEN-END:variables
}
