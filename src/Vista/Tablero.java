import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Tablero extends JPanel implements ComponentListener , ActionListener {
    
    private JButton[][] mCasillas = null ;
    
    private int mNumeroDeFilas = 10 ;
    
    private int mNumeroDeColumnas = 10 ;
    
    private int mSeparacion = 2 ; 
    
    public void acomodar() {
        
        int ancho = this.getWidth();
        
        int alto = this.getHeight();
        
        int dimensionMenor = Math.min( ancho , alto ); 
        
        int anchoDeCasilla = dimensionMenor / mNumeroDeColumnas ; 
        
        int altoDeCasilla = dimensionMenor / mNumeroDeFilas ;
        
        int xOffset = (ancho - dimensionMenor) / 2 ; 
        
        int yOffset = (alto - dimensionMenor) / 2 ; 
        
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
            
            for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                
                JButton temp = mCasillas[fila][columna] ;                            
                
                temp.setBounds(xOffset + columna * anchoDeCasilla, yOffset + fila * altoDeCasilla, anchoDeCasilla - mSeparacion, altoDeCasilla - mSeparacion );
                            
            }
            
        }
        
        
        
    }
    public Tablero() {        
        
        this.setBackground(Color.WHITE);
        
        this.addComponentListener(this);
        
        this.setLayout(null);                
        
    }

    public void inicializar() {
        
        mCasillas = new JButton[mNumeroDeFilas][mNumeroDeColumnas];
        
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
            
            for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                
                JButton temp = new JButton();
                
                temp.addActionListener(this);
                
                temp.setText("[" + fila + "],[" + columna + "]");                            
                
                mCasillas[fila][columna] = temp;                        
                
                this.add(temp);
                
            }
                
            
        }
    }

    public void componentResized(ComponentEvent e) {
        
        this.acomodar();
        
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void setNumeroDeFilas(int mNumeroDeFilas) {
        this.mNumeroDeFilas = mNumeroDeFilas;
    }

    public int getNumeroDeFilas() {
        return mNumeroDeFilas;
    }

    public void setNumeroDeColumnas(int mNumeroDeColumnas) {
        this.mNumeroDeColumnas = mNumeroDeColumnas;
    }

    public int getNumeroDeColumnas() {
        return mNumeroDeColumnas;
    }
    
    public void addItem(String item){
                 JButton temp = new JButton();
                
                temp.addActionListener(this);
                
                temp.setText(item);                            
                
               // mCasillas[fila][columna] = temp;                        
                
                temp.setBounds(10,10,90,50);
                this.add(temp);
    }

    private Random r = new Random();
    
    private Color getRandColor() {
    
        return new Color( r.nextInt(255), r.nextInt(255), r.nextInt(255) );
        
    }

    public void actionPerformed(ActionEvent e) {        
        
        if( e.getSource() instanceof JButton ) {
            
            JButton temp = (JButton) e.getSource() ;
            
            temp.setBackground( getRandColor() );
            
        }
        
    }
}
