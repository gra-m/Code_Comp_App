package fun.madeby.code_comp.outputdata;

import fun.madeby.code_comp.datatypes.outputtypes.DTOutput;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Meant to be passed to a reporting Interface and the expected reports be created based on template
 * type? or internal specification? todo ENUM of report types for report output?
 */
public interface DataSnapshot extends Serializable {

  default String getInfo() {
    return String.format(
        "Data_Type output = %s ZoneDateTime[internal timestamp] = %s",
        getDT_OUTPUT().getClass().getSimpleName(), getCREATED());
  }

  DTOutput getDT_OUTPUT();

  ZonedDateTime getCREATED();
}
