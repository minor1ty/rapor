module id.sch.pkbm31 {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.sql.rowset;
	requires javafx.base;
	requires jasperreports;

    opens id.sch.pkbm31 to javafx.fxml;
    opens id.sch.pkbm31.presentation;
    exports id.sch.pkbm31;
    exports id.sch.pkbm31.presentation;
}
