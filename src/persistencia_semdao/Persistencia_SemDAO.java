/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package persistencia_semdao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Persistencia_SemDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // BRUNO, se der erro ver se a porta, user e senha estão corretos em getConexao()
        
        Produto p = new Produto();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("escolha a opcao desejada: \n 1. cadastrar produto \n 2. deletar produto \n 3. atualizar produto \n 4. Pesquisar Produto por ID \n 5. Pesquisar todos \n  ");
            int escolha;
            escolha = sc.nextInt();
            if (escolha == 1){
                p.cadastrar();
                System.out.println("Cadastrado com sucesso.");
            }
            else if (escolha == 2){
                p.deletar();
                System.out.println("Deletado com sucesso.");
            }
            else if (escolha == 3){
                p.atualizar();
            }
            else if (escolha == 4){
               p = p.consultarById();
            if (p.getDescricao() != null) {
                System.out.println("ID............: " + p.getId());
                System.out.println("Descricao.....: " + p.getDescricao());
                System.out.println("Preco.........: " + p.getPreco());
            }
            else {
                System.out.println("ID não encontrado.");
            }
            }
            else if ( escolha == 5) {
                List<Produto> lprod = p.consultarTodos();
                    System.out.println("------------------------------------");
                    System.out.println("|--ID-------Descricao-------Preco--|");
                    System.out.println("------------------------------------");
                for (Produto prod : lprod) {
                    System.out.println(+prod.getId() +"-----------" +prod.getDescricao() +"-----------" + prod.getPreco());
                }
            }
            else {
                System.out.println("opcao nao encontrada, operacao encerrada.");
            }
        }catch(SQLException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        catch(ClassNotFoundException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
