package cloud.omdev;

import org.flywaydb.core.Flyway;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flywayMasterData(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:migrations/master_data")
                .schemas("schema_master_data")
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .table("flyway_schema_history")
                .load();
    }

    @Bean
    public Flyway flywayTransaksi(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:migrations/transaksi")
                .schemas("schema_transaksi")
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .table("flyway_schema_history")
                .load();
    }

    @Bean
    public ApplicationRunner flywayMigrationRunner(Flyway flywayMasterData, Flyway flywayTransaksi) {
        return args -> {
            flywayMasterData.migrate();
            flywayTransaksi.migrate();
        };
    }
}