package fr.esgi;

import fr.esgi.interfaces.PlanificationEntretiens;
import fr.esgi.interfaces.business.PlanificationEntretiensMock;
import fr.esgi.models.Candidat;
import org.junit.Assert;

public class PlanificationEntretiensTest {
    PlanificationEntretiens planificationEntretiens;

    private void init(){
        planificationEntretiens = new PlanificationEntretiensMock();
    }

    public void test_should_planifier_entretien()
    {
        init();
//        Assert.assertNotNull(planificationEntretiens.planifierEntretien(0,));
    }

    }
