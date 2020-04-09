package deepanshu.filters

import com.deepanshu.pojos.Demo1
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONException
import org.json.JSONObject

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class MyHttpFilter implements Filter{
    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        println "filter initiated for intercepting requests and responses"
    }

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        StringBuffer jb = new StringBuffer();
        String line = null;
        JSONObject jsonObject;
        try {
            InputStream inp = servletRequest.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            System.out.println(e);
        }

        if(jb.toString()){
            try{
                jsonObject = new JSONObject(jb.toString());
            }catch(JSONException ex){
                System.out.println("exception occured while parssing");
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        servletRequest?.request?.notes?.JSON = jsonObject;

        println "servletRequest is ${servletRequest}"
        println "resposne is ${servletResponse}"

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    void destroy() {

    }
}
