package todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MainApplication {
	 public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}


//	@Bean
//	public CommandLineRunner init(
//			@Autowired UserRepository userRepository,
//			@Autowired ProposalRepository proposalRepository
//			) {
//		return args -> {
//
//			Users joedoe = new Users("Ana");
//			userRepository.save(joedoe);
//
//			userRepository.save(new Users("Joao"));
//
//			System.out.println("LISTANDO usuários");
//			List<Users> users = userRepository.findAll();
//
//			System.out.println("LISTANDO usuários 222 "+ users);
//			users.forEach(System.out::println);
//
//			System.out.println("Encontrando por nome de usuários");
//			List<Users> porNome = userRepository.encontrarPorNome("Joao");
//			porNome.forEach(System.out::println);
//
//			Proposals p = new Proposals();
//			p.setUsers(joedoe);
//			p.setCreatedAt(LocalDate.now());
//			p.setValor(BigDecimal.valueOf(1542));
//			proposalRepository.save(p);
//
//			System.out.println("Atualizando usuários");
//			users.forEach(selectedUser -> {
//				selectedUser.setNome(selectedUser.getNome() + " atualizado");
//				userRepository.save(selectedUser);
//			});
//
////			Users proposalUser = userRepository.findUserFetchProposals(joedoe.getId());
////			System.out.println("Proposta do usuário" + proposalUser.getProposals());
//
//			System.out.println("buscando por Nome");
//			List<Users> selectedByNameUsers =  userRepository.findByNomeLike("Ana");
//			selectedByNameUsers.forEach(System.out::println);
//
//			boolean existe = userRepository.existsByNome("Ana");
//			System.out.println("Existe usuário com o nome Ana? " + existe);
//
//			System.out.println("Deletando usuário");
//			users.forEach(selectedUser -> {
//				userRepository.delete(selectedUser);
//			});
//
//			System.out.println("Depois de Deletar usuário");
//			List<Users> afterDeleteUsers = userRepository.findAll();
//
//			if (afterDeleteUsers.isEmpty()) {
//				System.out.println("Nenhum usuário encontrado.");
//			} else {
//				afterDeleteUsers.forEach(System.out::println);
//			}
//		};
//	}