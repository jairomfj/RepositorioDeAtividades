package br.com.repositoriodeatividades.usecases.activities.create;

import br.com.repositoriodeatividades.usecases.activities.create.vo.CreateActivityParameters;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateActivity {

    public void execute(List<CreateActivityParameters> createActivityParametersList) throws Exception {

        if(createActivityParametersList.size() == 0) {
            throw new IllegalArgumentException("No data has been passed");
        }

    }


}
