/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia_semdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto {

    private int id;
    private String descricao;
    private double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void cadastrar() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("insert into produto(descricao, preco) values(?,?)");
        Scanner sc = new Scanner(System.in);
        System.out.println("digite a descricao do produto a ser cadastrado: ");
        descricao = sc.nextLine();
        System.out.println("digite o preco do produto a ser cadastrado: ");
        preco = sc.nextDouble();
        comando.setString(1, descricao);
        comando.setDouble(2, preco);
        comando.execute();
        con.close();
    }

    public void deletar() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("delete from produto where id = ?");
        Scanner sc = new Scanner(System.in);
        System.out.println("digite o id do produto a ser deletado: ");
        id = sc.nextInt();
        comando.setInt(1, id);
        comando.execute();
        con.close();
    }

    public void atualizar() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        Scanner sc = new Scanner(System.in);
        System.out.println("digite o id do produto a ser atualizado: ");
        id = sc.nextInt();
        sc.nextLine();
        PreparedStatement verificaProduto = con.prepareStatement("SELECT COUNT(*) FROM produto WHERE id = ?");
    verificaProduto.setInt(1, id);
    
    ResultSet rs = verificaProduto.executeQuery();
    rs.next();
    int count = rs.getInt(1);
        if (count == 0) {
        System.out.println("Produto com ID " + id + " nao encontrado.");
    } else {
        PreparedStatement comando = con.prepareStatement("update produto set descricao = ?, preco = ? where id = ?");
        System.out.println("digite a nova descricao do produto: ");
        descricao = sc.nextLine();
        System.out.println("digite o novo preco do produto: ");
        preco = sc.nextDouble();
        comando.setString(1, descricao);
        comando.setDouble(2, preco);
        comando.setInt(3, id);
        comando.execute();
        con.close();
        System.out.println("Atualizado com sucesso.");
        }
    }

    public Produto consultarById() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        String SQL = "select * from produto where id = ?";
        PreparedStatement comando = con.prepareStatement(SQL);
        Scanner sc = new Scanner(System.in);
        System.out.println("digite o id do produto a ser pesquisado: ");
        id = sc.nextInt();
        comando.setInt(1, id);
        ResultSet rs = comando.executeQuery();
        Produto p = new Produto();
        if (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setPreco(rs.getDouble("preco"));

        }
        con.close();
        return p;
    }

    public List<Produto> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        String SQL = "select * from  produto";
        PreparedStatement comando = con.prepareStatement(SQL);
        ResultSet resultado = comando.executeQuery();
        List<Produto> listaprodutos = new ArrayList<Produto>();
        while(resultado.next()) {
            Produto prod = new Produto();
            prod.setId(resultado.getInt("id"));
            prod.setDescricao(resultado.getString("descricao"));
            prod.setPreco(resultado.getDouble("preco"));
            listaprodutos.add(prod);
            
        }
        
        con.close();
        return listaprodutos;
    }
        
    

    public Connection getConexao() throws ClassNotFoundException, SQLException {
        //Database properties: verificar em seu computador
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/aula_ioo"; // colocar a porta que voce cadastrou na hora que baixou o mysql.
        String USER = "root"; // colocar o user que voce cadastrou na hora que baixou o mysql.
        String PASSWORD = ""; // colocar a senha que voce cadastrou na hora que baixou o mysql.
        // O método forName carrega e inicia o driver passado por parâmetro
        Class.forName(DRIVER);
        // Estabele a conexão
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
