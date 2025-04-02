package gradehorario;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// Classe responsável por gerenciar a criação de arquivos, especialmente planilhas Excel
public class GerenciadorDeArquivos {
    private String nomePasta; // Nome da pasta onde os arquivos serão salvos

    // Construtor para inicializar o nome da pasta
    public GerenciadorDeArquivos(String nomePasta) {
        this.nomePasta = nomePasta;
        criarPasta();
    }

    // Método para criar a pasta se ela não existir
    public void criarPasta() {
        File pasta = new File(nomePasta);
        if (!pasta.exists()) {
            if (pasta.mkdirs()) {
                System.out.println("Pasta " + nomePasta + " criada com sucesso.");
            } else {
                System.out.println("Falha ao criar a pasta " + nomePasta + ".");
            }
        }
    }

    // Método para gerar o nome do arquivo Excel com base na turma
    public String gerarNomeArquivo(Turma turma) {
        return nomePasta + "/" + turma.getNome() + ".xlsx";
    }

    // Método para criar a planilha Excel com os horários da turma
    public void criarPlanilhaExcel(Turma turma) {
        String nomeArquivoExcel = gerarNomeArquivo(turma);

        try (Workbook workbook = new XSSFWorkbook()) { // Cria um novo workbook para a planilha
            Sheet sheet = workbook.createSheet("Horário"); // Cria uma nova folha na planilha

            // Criar estilo para os cabeçalhos e horários em negrito
            CellStyle boldStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldStyle.setFont(boldFont);

            // Criar cabeçalhos
            Row headerRow = sheet.createRow(0); // Cria a primeira linha para os cabeçalhos
            headerRow.createCell(0).setCellValue(""); // Celula vazia para o canto superior esquerdo
            String[] diasDaSemana = {"               Segunda", "               Terça", "               Quarta", "               Quinta", "               Sexta"};
            for (int i = 0; i < diasDaSemana.length; i++) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(diasDaSemana[i]);
                cell.setCellStyle(boldStyle); // Aplicar o estilo em negrito
                // Definir largura das colunas
                sheet.setColumnWidth(i + 1, 25 * 256); // Define a largura das colunas para 25 caracteres
            }

            // Definir largura da primeira coluna
            sheet.setColumnWidth(0, 12 * 256); // Define a largura da primeira coluna para 12 caracteres

            // Criar linhas de horários
            for (int horario = 0; horario < 5; horario++) { // Loop para criar linhas para cada horário
                Row row = sheet.createRow(horario + 1);
                Cell cell = row.createCell(0);
                cell.setCellValue((horario + 1) + "ª Aula");
                cell.setCellStyle(boldStyle); // Aplicar o estilo em negrito

                for (int dia = 0; dia < 5; dia++) { // Loop para preencher as células com as matérias
                    Materia[] materias = turma.getPosicaoDia(dia + 1);
                    Materia materia = materias[horario];
                    String materiaProfessor = materia.getNome() + " - Prof. " + materia.getProfessor().getNome();
                    row.createCell(dia + 1).setCellValue(materiaProfessor);
                }
            }

            // Salva a planilha no arquivo
            try (FileOutputStream fileOut = new FileOutputStream(nomeArquivoExcel)) {
                workbook.write(fileOut);
                System.out.println("Planilha Excel criada com sucesso: " + nomeArquivoExcel);
            } catch (IOException e) {   
                System.err.println("Erro ao escrever na planilha Excel: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar a planilha Excel: " + e.getMessage());
        }
    }
}
