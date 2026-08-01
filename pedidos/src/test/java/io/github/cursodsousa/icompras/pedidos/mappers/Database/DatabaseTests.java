package io.github.cursodsousa.icompras.pedidos.mappers.Database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class DatabaseTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void deveConectarConexao() throws Exception {
        try (Connection connection = dataSource.getConnection())
        {
//            verifica objeto connection esta criado e nao nulo
            assertNotNull(connection);
//            verifica se existe connecao aberta
            assertFalse(connection.isClosed());

            System.out.println("Conectado com sucesso");
        }


    }
}


//@SpringBootTest
//public class DatabaseTests {
//    @Autowired
//    private DataSource dataSource;
//
//    @Test
//    void deveConectarAoBanco() throws Exception {
//        try (Connection connection = dataSource.getConnection()) {
//
//            assertNotNull(connection);
//            assertFalse(connection.isClosed());
//
//            System.out.println("Conexão com o banco realizada com sucesso!");
//        }
//    }
//
//    @Test
//    void deveVerificarSeBancoIcomprasClientesExiste() throws Exception {
//
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//
//            String sql = """
//                    SELECT EXISTS (
//                        SELECT FROM pg_database
//                        WHERE datname = 'icomprasclientes'
//                    )
//                    """;
//
//            try (ResultSet result = statement.executeQuery(sql)) {
//
//                assertTrue(result.next());
//
//                boolean existe = result.getBoolean(1);
//
//                assertTrue(
//                        existe,
//                        "O banco icomprasclientes não existe!"
//                );
//
//                System.out.println(
//                        "Banco icomprasclientes existe: " + existe
//                );
//            }
//        }
//    }
//
//    @Test
//    void deveVerificarSeTabelaClientesExiste() throws Exception {
//
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//
//            String sql = """
//                    SELECT EXISTS (
//                        SELECT FROM information_schema.tables
//                        WHERE table_schema = 'public'
//                        AND table_name = 'clientes'
//                    )
//                    """;
//
//            try (ResultSet result = statement.executeQuery(sql)) {
//
//                assertTrue(result.next());
//
//                boolean existe = result.getBoolean(1);
//
//                assertTrue(
//                        existe,
//                        "A tabela clientes não existe!"
//                );
//
//                System.out.println(
//                        "Tabela clientes existe: " + existe
//                );
//            }
//        }
//    }
//}
