package fr.esgi;

import fr.esgi.interfaces.PlanificationEntretiens;
import fr.esgi.interfaces.business.PlanificationEntretiensMock;
import fr.esgi.models.Candidat;
import fr.esgi.models.Compétence;
import fr.esgi.models.CreneauHoraire;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanificationEntretiensTest extends TestCase {
    PlanificationEntretiens planificationEntretiens;

    private void init(){
        planificationEntretiens = new PlanificationEntretiensMock();
    }

    public void test_should_planifier_entretien()
    {
        init();
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.AGILITE);
        Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
        Assert.assertTrue(planificationEntretiens.planifierEntretien(candidat,creneauHoraire));
    }

    }
