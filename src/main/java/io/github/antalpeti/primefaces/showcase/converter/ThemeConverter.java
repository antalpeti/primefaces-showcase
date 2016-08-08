package io.github.antalpeti.primefaces.showcase.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import io.github.antalpeti.primefaces.showcase.domain.Theme;
import io.github.antalpeti.primefaces.showcase.service.ThemeService;

@FacesConverter("themeConverter")
public class ThemeConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (value != null && value.trim().length() > 0) {
      try {
        ThemeService service =
            (ThemeService) fc.getExternalContext().getApplicationMap().get("themeService");
        return service.getThemes().get(Integer.parseInt(value));
      } catch (NumberFormatException e) {
        throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Conversion Error", "Not a valid theme."));
      }
    } else {
      return null;
    }
  }

  @Override
  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if (object != null) {
      return String.valueOf(((Theme) object).getId());
    } else {
      return null;
    }
  }
}
