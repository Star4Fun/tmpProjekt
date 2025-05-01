/**
 * The main module of the mm application.
 */
module mm {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires transitive jbox2d.library;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;
    requires javafx.base;
    
    exports mm.render;
    exports mm.io;
    exports mm.model.physics;
    exports mm.model.level;
}
