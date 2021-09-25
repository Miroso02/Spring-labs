package projectFiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController
{
    @GetMapping ("/mirosoInfo")
    public String mirosoButton()
    {
        return "ggwpwp";
    }

    @GetMapping("/leohostnameInfo")
    public String leoButton()
    {
        return "index";
    }
}
