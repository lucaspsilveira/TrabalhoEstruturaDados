package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.util.Calendar;

import TADAtendimento.TadFilaAtendimento;
import TADAtendimento.TadListaAtendimento;
import TADPaciente.TadListaPaciente;
import model.Atendimento;
import model.Paciente;

public class Main {
	public static void main(String[] args) {
		Tela tela = new Tela();
		TadListaPaciente listaPacientes = new TadListaPaciente();
		TadListaAtendimento listaAtendimentosEncerrados = new TadListaAtendimento();
		TadFilaAtendimento filaAtendimentos = new TadFilaAtendimento();
		TadFilaAtendimento filaAtendimentos1 = new TadFilaAtendimento();
		TadFilaAtendimento filaAtendimentos2 = new TadFilaAtendimento();
		TadFilaAtendimento filaAtendimentos3 = new TadFilaAtendimento();
		TadFilaAtendimento filaAtendimentos4 = new TadFilaAtendimento();
		TadFilaAtendimento filaAtendimentos5 = new TadFilaAtendimento();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			boolean sair = false;
			Paciente pacienteEncontrado = null;
			Paciente paciente = null;
			do {
				tela.mostarMenu();
				String res = bf.readLine();
				switch (res.toLowerCase()) {
				case "1":
					listaPacientes.adicionarNoComeco(tela.inserePaciente(null));
					break;
				case "2":
					pacienteEncontrado = listaPacientes.retornaNodoPacienteCpf(tela.realizaPerguntaSimples("Digite o CPF do paciente que desejas pesquisar: (pontuação irá ser desconsiderada) ", "numero"));
					if (pacienteEncontrado == null)
						System.out.println("Paciente não Encontrado");
					else
						pacienteEncontrado.imprimePaciente();
					
					break;
				case "3":
					Atendimento atendimento = new Atendimento();
					String cpf = tela.realizaPerguntaSimples("Digite o CPF do paciente que desejas iniciar o atendimento: (pontuação irá ser desconsiderada) ", "numero");
					pacienteEncontrado = listaPacientes.retornaNodoPacienteCpf(cpf);
					if (pacienteEncontrado == null) {
						paciente = new Paciente();
						System.out.println("Paciente não encontrado");
						System.out.println("Criando o cadastro:");
						paciente = tela.inserePaciente(cpf);
						listaPacientes.adicionarNoComeco(paciente);
						pacienteEncontrado = paciente;
					}
					atendimento.setPessoa(pacienteEncontrado);
					atendimento.setHoraChegada(Calendar.getInstance());
					filaAtendimentos.enqueue(atendimento);
					System.out.println("Paciente "+ pacienteEncontrado.getNome()+ " adicionado na fila!");
					break;
				case "4":
					if (filaAtendimentos.isEmpty()) {
						System.out.println("Fila de atendimento vazia!");
					} else {
						Atendimento atendimentoTriagem = tela.realizaTriagem(filaAtendimentos.dequeue());
						atendimentoTriagem.setHoraAtendimento(Calendar.getInstance());
						System.out.println("Paciente " + atendimentoTriagem.getPessoa().getNome() + " vai pra fila de prioridade: "+ atendimentoTriagem.getPrioridade());
						switch (atendimentoTriagem.getPrioridade()) {
							case 1:
								filaAtendimentos1.enqueue(atendimentoTriagem);
								break;
							case 2:
								filaAtendimentos2.enqueue(atendimentoTriagem);
								break;
							case 3:
								filaAtendimentos3.enqueue(atendimentoTriagem);
								break;
							case 4:
								filaAtendimentos4.enqueue(atendimentoTriagem);
								break;
							case 5:
								filaAtendimentos5.enqueue(atendimentoTriagem);
								break;
						}
					}
		
					break;
				case "5":
					Atendimento consulta = new Atendimento();
					if(!filaAtendimentos1.isEmpty()) {
						consulta = filaAtendimentos1.dequeue();
						System.out.println("Paciente " + consulta.getPessoa().getNome() + " encaminhado para a consulta.");
						listaAtendimentosEncerrados.adicionarNoFinal(consulta);
					}else if (!filaAtendimentos2.isEmpty()) {
						consulta = filaAtendimentos2.dequeue();
						System.out.println("Paciente " + consulta.getPessoa().getNome() + " encaminhado para a consulta.");
						listaAtendimentosEncerrados.adicionarNoFinal(consulta);
					}else if(!filaAtendimentos3.isEmpty()) {
						consulta = filaAtendimentos3.dequeue();
						System.out.println("Paciente " + consulta.getPessoa().getNome() + " encaminhado para a consulta.");
						listaAtendimentosEncerrados.adicionarNoFinal(consulta);
					}else if(!filaAtendimentos4.isEmpty()) {
						consulta = filaAtendimentos4.dequeue();
						System.out.println("Paciente " + consulta.getPessoa().getNome() + " encaminhado para a consulta.");
						listaAtendimentosEncerrados.adicionarNoFinal(consulta);
					}else if(!filaAtendimentos5.isEmpty()) {
						consulta = filaAtendimentos5.dequeue();
						System.out.println("Paciente " + consulta.getPessoa().getNome() + " encaminhado para a consulta.");
						listaAtendimentosEncerrados.adicionarNoFinal(consulta);
					}else {
						System.out.println("Todas as filas estão vazias.");
					}
					break;
				case "6":
					Atendimento liberacao = new Atendimento(); //Tem que remover da lista de liberação pra não ter como liberar o mesmo paciente de novo?
					liberacao = listaAtendimentosEncerrados.liberarPacienteAtendimento(tela.realizaPerguntaSimples("Digite o CPF do paciente que desejas liberar: (pontuação irá ser desconsiderada) ", "numero"));
					if (liberacao == null)
						System.out.println("Atendimento do paciente não encontrado.");
					else
						System.out.println("Paciente " + liberacao.getPessoa().getNome() + " liberado as " + liberacao.getHoraSaida().getTime());
					break;
				case "7":
					break;
				case "debug":
					System.out.println("Lista de Pacientes");
					listaPacientes.imprimir();
					System.out.println("");
					System.out.println("Fila de Atendimentos");
					filaAtendimentos.imprimir();
					System.out.println("Fila de atendimentos prioridade 1");
					filaAtendimentos1.imprimir();
					System.out.println("Fila de atendimentos prioridade 2");
					filaAtendimentos2.imprimir();
					System.out.println("Fila de atendimentos prioridade 3");
					filaAtendimentos3.imprimir();
					System.out.println("Fila de atendimentos prioridade 4");
					filaAtendimentos4.imprimir();
					System.out.println("Fila de atendimentos prioridade 5");
					filaAtendimentos5.imprimir();
					break;
				case "sair":
					sair = true;
					break;
				default:
					System.out.println("Insira um valor válido.");
				}
			} while (!sair);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Sistema encerrado.");
	}
}
