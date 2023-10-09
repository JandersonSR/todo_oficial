package todo.domains.repository;

import todo.domains.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    List<UserEntity> findByNomeLike(String nome);
    List<UserEntity> findByNomeLikeOrId(String nome, Long id);
    List<UserEntity> findByNomeLikeOrIdOrderById(String nome, Long id);

    Optional<UserEntity> findOneByNome(String nome);

    Optional<UserEntity> findOneByEmail(String email);

//    @Query(" select u from users u left join fetch u.proposals where u.id = :id ")
//    Users findUserFetchProposals(@Param("id") Integer id);

    @Query(value = " select * from users u where u.nome like '%:nome%' ", nativeQuery = true)
    List<UserEntity> encontrarPorNome(@Param("nome") String nome);


    boolean existsByNome(String nome);
}
