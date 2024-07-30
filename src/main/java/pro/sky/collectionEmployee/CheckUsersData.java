package pro.sky.collectionEmployee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.collectionEmployee.exceptions.exceptions.exceptions.InvalidNameException;

@Service
public class CheckUsersData {
    public String checkFirstName(String param){
        if (!StringUtils.isAlpha(param)){
            throw new InvalidNameException(param);
        }
        return StringUtils.capitalize(param.toLowerCase());
    }

}
