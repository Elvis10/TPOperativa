import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    private Tablero mTablero = new Tablero();
    
    public MainFrame(List<String> items) {
        try {
            jbInit(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit(List<String> items) throws Exception {
        
        
        this.setLayout(new BorderLayout());
        this.setSize( new Dimension(400, 300) );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.mTablero = new Tablero();
        
        this.add(mTablero, BorderLayout.CENTER );
                    
        mTablero.setNumeroDeColumnas(items.size());
        mTablero.setNumeroDeFilas(items.size());
        
        mTablero.addItem("hola me agregue");
        
        mTablero.inicializar();
                
    }
}
