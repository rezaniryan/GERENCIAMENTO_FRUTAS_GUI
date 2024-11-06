package GRAPHICAL_USER_INTERFACE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrutaManagerGUI {
		
	    // arraylist para amazenar as frutas
	    private ArrayList<String> frutas;
	    private DefaultListModel<String> listModel;
	    private JList<String> list;

	    // construtor onde configura a lista FrutaMangerGUI
	    public FrutaManagerGUI() {
	    	
	        // inicia o array
	        frutas = new ArrayList<>();
	        listModel = new DefaultListModel<>();

	        // cria a janela gerenciador de frutas
	        JFrame frame = new JFrame("Gerenciador de Frutas");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 300);
	        frame.setLayout(new BorderLayout());

	        // cria o painel para a interaçao com as frutas
	        JPanel panel = new JPanel();
	        panel.setLayout(new FlowLayout());

	        // barra para difitar o nome da fruta
	        JTextField frutaField = new JTextField(15);
	        panel.add(frutaField);
	        
	        // botao para adicionar uma nova fruta
	        JButton addButton = new JButton("Adicionar"); 
	        panel.add(addButton);

	        // botao para modificar a fruta existente
	        JButton modifyButton = new JButton("Modificar");
	        
	        // inicialmente desativado
	        modifyButton.setEnabled(false);
	        panel.add(modifyButton);
	        
	        // remove a fruta escolida
	        JButton removeButton = new JButton("Remover");
	        // inicialmente desativado
	        removeButton.setEnabled(false);
	        panel.add(removeButton);

	        // coloca o painel no topo do frame
	        frame.add(panel, BorderLayout.NORTH);

	        // configura a lista para a escolha de um iten por vez
	        list = new JList<>(listModel);
	        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JScrollPane scrollPane = new JScrollPane(list);
	        frame.add(scrollPane, BorderLayout.CENTER);

	        // botao para listar todas as frutas
	        JButton listButton = new JButton("Listar Frutas");
	        frame.add(listButton, BorderLayout.SOUTH);

	        // adiciona a fruta " adicionar "
	        addButton.addActionListener(new ActionListener() {
	            
	        	@Override
	            public void actionPerformed(ActionEvent e) {
	        		// captura o texto colocado
	                String novaFruta = frutaField.getText();
	                
	                // identifica se o campo esta vazio
	                if (!novaFruta.isEmpty()) { 
	                	// adiciona a fruta ao ArrayList
	                    frutas.add(novaFruta); 
	                 	// adiciona a fruta à lista visual
	                    listModel.addElement(novaFruta); 
	                 	// limpa o campo de texto
	                    frutaField.setText(""); 
	                 	// mensagem de confirmaçao
	                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada."); 
	                }
	            }
	        });

	        // modifica a fruta selecionada " modificar "
	        modifyButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	 // captura o intem selecionado
	                int selectedIndex = list.getSelectedIndex();
	                // identifica se possui algum item selecionado
	                if (selectedIndex != -1) { 
	                    String frutaSelecionada = listModel.getElementAt(selectedIndex);
	                    // mostra a barra para adicionar uma nova fruta 
	                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
	                    // identifica o texto do usuario
	                    if (novaFruta != null && !novaFruta.isEmpty()) {
	                    	// atualiza o array
	                        frutas.set(selectedIndex, novaFruta); 
	                        // atualiza a lista
	                        listModel.set(selectedIndex, novaFruta); 
	                        JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta + ".");
	                    }
	                } else {
	                	// mostra o texto para selecionar uma fruta 
	                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar."); 
	                }
	            }
	        });

	        // remove a fruta ao clicar no botao " remover "
	        removeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	// captura o indice do item selecionado
	                int selectedIndex = list.getSelectedIndex(); 
	                if (selectedIndex != -1) {
	                	 // remove a fruta do array
	                    String frutaRemovida = frutas.remove(selectedIndex);
	                    // remove a fruta da lista 
	                    listModel.remove(selectedIndex);
	                    JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida.");
	                } else {
	                	// mensagem se nao selecionar nenhuma fruta
	                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover."); 
	                }
	            }
	        });

	        // mostra a lista de todas as frutas ao clicar no botao " listar fruta "
	        listButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (frutas.isEmpty()) {
	                	// se a lista estiver vazia
	                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista."); 
	                } else {
	                	// mostra todas as frutas no array
	                    JOptionPane.showMessageDialog(frame, "Frutas: " + frutas); 
	                }
	            }
	        });

	        // ativa e desativa o modificar e remover
	        list.addListSelectionListener(e -> {
	            boolean selectionExists = !list.isSelectionEmpty();
	            removeButton.setEnabled(selectionExists);
	            modifyButton.setEnabled(selectionExists);
	        });

	        // monta o frame visível
	        frame.setVisible(true);
	    }

	    // inicializa a aplicaçao
	    public static void main(String[] args) {
	        // cria a interface na thread separada
	        SwingUtilities.invokeLater(() -> new FrutaManagerGUI());
	    }
	}


