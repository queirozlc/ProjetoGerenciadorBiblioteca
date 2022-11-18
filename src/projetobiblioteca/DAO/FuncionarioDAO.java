
package projetobiblioteca.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import projetobiblioteca.model.Funcionario;

/**
 *
 * @author Lucas
 */
public class FuncionarioDAO implements IDAO {

    @Override
    public void criaDatabase() throws IOException {
        // busca diretorio onde está o projeto
        File arquivo = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
        PrintWriter writer = null;

        if (!arquivo.exists()) {
            arquivo.createNewFile();
            try {
                FileWriter out = new FileWriter(arquivo, true);
                writer = new PrintWriter(out);
                writer.println("Matricula;Nome;Endereco;Data Ingresso;Login;Senha;Setor");

            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());

            } finally {
                writer.close();
            }
        }

    }

    public void insert(Funcionario funcionario) {
        File arquivo = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
        PrintWriter writer = null;

        if (arquivo.exists()) {

            try {
                FileWriter out = new FileWriter(arquivo, true);
                writer = new PrintWriter(out);

                writer.write(funcionario.getMatricula() + ";" + funcionario.getNome() + ";" + funcionario.getEndereco() + ";" + funcionario.getDataFormatada() + ";" + funcionario.getLogin() + ";" + funcionario.getSenha() + ";" + funcionario.getSetor() + "\n");
                writer.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());

            } finally {
                writer.close();
            }

        }
    }

    @Override
    public void delete(int id) {

    }

    public void update(Funcionario funcionario) {

    }

    @Override
    public List<Object> selectAll() {
        return null;
    }

    public boolean verificaMatriculaELogin(int matriculaAComparar, String loginAComparar) throws FileNotFoundException, IOException {
        File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
        int matricula = 0;
        String login = "";
        
        try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String linha = reader.readLine();
            linha = reader.readLine();
            
            
            while (linha != null) {
                String[] linhaSplit = linha.split(";");

                matricula = Integer.parseInt(linhaSplit[0]);
                login = linhaSplit[4];

                if (matricula == matriculaAComparar || login.equalsIgnoreCase(loginAComparar)) {
                    return true;
                }
                
                linha = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
    
    
    
    public boolean pesquisarPorLoginESenha(String login, String senha) {
        File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String loginBanco, senhaBanco;
            String linha = reader.readLine();
            linha = reader.readLine();
            
            while (linha != null) {
                
                String[] linhaSplit = linha.split(";");
                loginBanco = linhaSplit[4];
                senhaBanco = linhaSplit[5];
                
                if (loginBanco.equalsIgnoreCase(login) && senhaBanco.equalsIgnoreCase(senha)) {
                    return true;
                }
                
                linha = reader.readLine();
            }
            
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}

    
