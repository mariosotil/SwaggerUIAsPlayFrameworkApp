import play.api.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.HttpFilters;

import javax.inject.Inject;

/**
 * This class is necessary for configure CORS
 */
public class Filters implements HttpFilters {

    @Inject
    CORSFilter corsFilter;

    public EssentialFilter[] filters() {
        return new EssentialFilter[] { corsFilter };
    }
}