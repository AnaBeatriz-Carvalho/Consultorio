package Consultorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Medico> listaM = new ArrayList<>();
        ArrayList<Paciente> listaP = new ArrayList<>();
        ArrayList<Consulta> listaC = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);

        int opcao = 1000;
        double contador = 0;
        while (opcao != 0) {
            System.out.println(
                    "Escolha um opção:\n0 - Sair\n1 - Cadastro de Médicos\n2 - Cadastro de Pacientes\n3 - Cadastro de Consultas\n"
                    + "4 - Cancelamento de Consultas\n5 - Relatório de Consultas Agendadas\n6 - Relatório Financeiro");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    Medico medico = new Medico();
                    System.out.println("----------Cadastro de Médico----------");
                    System.out.println("CPF:");
                    medico.setCpf(entrada.next());
                    System.out.println("CRM:");
                    medico.setCrm(entrada.next());
                    System.out.println("Nome:");
                    medico.setNome(entrada.next());
                    System.out.println("Data de Nascimento [dd/MM/yyyy]: ");
                    medico.setDataNascimento(formato.parse(entrada.next()));
                    System.out.println("Data de Cadastro [dd/MM/yyyy]: ");
                    medico.setDataCadastro(formato.parse(entrada.next()));
                    System.out.println("Médico cadastrado com sucesso!");
                    listaM.add(medico);
                    break;

                case 2:
                    Paciente paciente = new Paciente();
                    System.out.println("----------Cadastro de Paciente----------");
                    System.out.println("CPF:");
                    paciente.setCpfP(entrada.next());
                    System.out.println("Nome:");
                    paciente.setNome(entrada.next());
                    System.out.println("Data de Nascimento [dd/MM/yyyy]: ");
                    paciente.setDataNascimento(formato.parse(entrada.next()));
                    System.out.println("Data de Cadastro [dd/MM/yyyy]: ");
                    paciente.setDataCadastro(formato.parse(entrada.next()));
                    System.out.println("Paciente cadastrado com sucesso!");
                    listaP.add(paciente);
                    break;

                case 3:

                    Consulta consulta = new Consulta();
                    System.out.println("----------Cadastro de Consulta----------");
                    System.out.println("CPF do paciente:");
                    consulta.getCpfP(entrada.next());
                    System.out.println("CRM:");
                    consulta.getCrm(entrada.next());
                    System.out.println("Data da Consulta [dd/MM/yyyy]:");
                    consulta.setDataConsulta(formato.parse(entrada.next()));
                    System.out.println("Hora da Consulta:");
                    consulta.setHoraConsulta(entrada.next());
                    System.out.println("Retorno?[true/false]:");
                    consulta.setConsultaRetorno(entrada.nextBoolean());
                    if (consulta.getConsultaRetorno() == true){
                        System.out.println("Qual a data da última consulta?:");
                        Date data_1 = (consulta.getDataConsulta());
                        Date data_2 = (formato.parse(entrada.next()));
                        long diff = data_2.getTime() - data_1.getTime();
                        if (diff <= 30){
                            contador += 0;
                            consulta.setValorConsulta(0);
                        } else{
                            contador += 280;
                            consulta.setValorConsulta(280);
                        }
                    } else {
                        contador += 280;
                        consulta.setValorConsulta(280);
                    }
                    consulta.setStatusConsulta("Agendada");
                    System.out.println();
                    System.out.println("Consulta agendada com sucesso!");
                    System.out.println();
                    listaC.add(consulta);
                    break;

                case 4:

                    System.out.println("CPF para verificação:");
                    String consultaCPF = entrada.next();
                    System.out.println("Data da consulta para verificação:");
                    Date consultaData = (formato.parse(entrada.next()));
                    System.out.println("CRM para verificação:");
                    String consultaCrm = entrada.next();

                    String cpf_P = null;
                    Date data_C = null;
                    String crm_M = null;
                    int indice_cpf = 0;
                    int indice_data = 0;
                    int indice_crm = 0;

                    for (int i = 0; i < listaP.size(); i++) {
                        if (consultaCPF.equals(listaP.get(i).getCpfP())) {
                            cpf_P = consultaCPF;
                            indice_cpf = i;
                        }
                    }
                    for (int j = 0; j < listaC.size(); j++) {
                        if (consultaData.equals(listaC.get(j).getDataConsulta())) {
                            data_C = consultaData;
                            indice_data = j;
                        }
                    }
                    for (int k = 0; k < listaM.size(); k++) {
                        if (consultaCrm.equals(listaM.get(k).getCrm())) {
                            crm_M = consultaCrm;
                            indice_crm = k;
                        }
                    }
                    if (consultaCrm.equals(crm_M)) {
                        if (consultaCPF.equals(cpf_P)) {
                            if (consultaData.equals(data_C)) {
                                
                                listaC.get(indice_data).setStatusConsulta("Cancelada");
                                System.out.println("Nome do paciente:" + listaP.get(indice_cpf).getNome() + "\n"
                                        + "CPF do paciente:" + listaP.get(indice_cpf).getCpfP() + "\n" + "CRM:" + listaM.get(indice_crm).getCrm() + "\n"
                                        + "Status da consulta:" + listaC.get(indice_data).getStatusConsulta());
                                double valor = listaC.get(indice_data).getValorConsulta();
                                contador -= valor;
                            } else {
                                System.out.println("Data não encontrada!");
                            }
                        } else {
                            System.out.println("Usuário não encontrado!");
                        }
                    } else {
                        System.out.println("CRM não encontrado!");
                    }
                    break;

                case 5:
                    System.out.println("Qual a data desejada?[dd/mm/yyyy]:");
                    Date data = (formato.parse(entrada.next()));
                    System.out.println("Qual o crm do médico?:");
                    String crm = entrada.next();
                    
                    for (int i = 0; i < listaC.size(); i++) {
                        if (listaC.get(i).getStatusConsulta().equals("Cancelada")) {
                            i = i;
                        } else {
                            if (listaC.get(i).getDataConsulta().equals(data)){
                                System.out.println("Valor da consulta:" + listaC.get(i).getValorConsulta());
                                System.out.println("Consulta de retorno:" + listaC.get(i).getConsultaRetorno());
                                System.out.println("Status da consulta:" + listaC.get(i).getStatusConsulta());
                                System.out.println("Horário da consulta:" + listaC.get(i).getHoraConsulta());
                                System.out.println("Data da consulta:" + listaC.get(i).getDataConsulta());
                                System.out.println();
                            } else {
                                i = i;
                            }
                        }
                    }
                    break;
                    
                case 6:
                    System.out.println("qual a data desejada?");
                    Date dataf = (formato.parse(entrada.next()));
                    for (int i = 0; i < listaC.size(); i++) {
                        if (listaC.get(i).getDataConsulta().equals(dataf)){
                        	System.out.println("Valor da consulta:" + listaC.get(i).getValorConsulta());
                            System.out.println("Consulta de retorno:" + listaC.get(i).getConsultaRetorno());
                            System.out.println("Status da consulta:" + listaC.get(i).getStatusConsulta());
                            System.out.println("Horário da consulta:" + listaC.get(i).getHoraConsulta());
                            System.out.println("Data da consulta:" + listaC.get(i).getDataConsulta());
                            System.out.println();
                            
                        }
                    }
                    System.out.println("Valor total:" + contador);
            }		break;

        }

    }
}