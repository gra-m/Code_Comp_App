package fun.madeby.code_comp_app.outputdata;

import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Meant to be passed to a reporting Interface and the expected reports be created based on template
 * type? or internal specification? todo ENUM of report types for report output? Data snapshots would be retrievable
 */
public interface DataSnapshot extends Serializable {

  default String DataSnapshotInfoString() {
    return String.format(
        "Data_Type output = %s ZoneDateTime[internal timestamp] = %s",
        getDT_OUTPUT().getClass().getSimpleName(), getCREATED());
  }



  DTOutput getDT_OUTPUT();

  ZonedDateTime getCREATED();
}
