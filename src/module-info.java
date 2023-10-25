/**
 * 
 */
/**
 * 
 */
module EjercicioL {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
    requires javafx.controls;
	requires javafx.base;
	opens application to javafx.base, javafx.graphics, javafx.fxml;
}