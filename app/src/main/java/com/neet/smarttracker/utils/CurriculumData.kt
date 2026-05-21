package com.neet.smarttracker.utils

object CurriculumData {
    val physicsChapters = listOf(
        "Electric Charges and Fields",
        "Electrostatic Potential and Capacitance",
        "Current Electricity",
        "Moving Charges and Magnetism",
        "Magnetism and Matter",
        "Electromagnetic Induction",
        "Alternating Current",
        "Electromagnetic Waves",
        "Ray Optics and Optical Instruments",
        "Wave Optics",
        "Dual Nature of Radiation and Matter",
        "Atoms",
        "Nuclei",
        "Semiconductor Electronics",
        "Communication Systems"
    )

    val chemistryChapters = listOf(
        "Solid State",
        "Solutions",
        "Electrochemistry",
        "Chemical Kinetics",
        "Surface Chemistry",
        "General Principles and Processes of Isolation of Elements",
        "The p-Block Elements",
        "The d- and f-Block Elements",
        "Coordination Compounds",
        "Haloalkanes and Haloarenes",
        "Alcohols, Phenols and Ethers",
        "Aldehydes, Ketones and Carboxylic Acids",
        "Organic Compounds Containing Nitrogen",
        "Biomolecules",
        "Polymers",
        "Chemistry in Everyday Life"
    )

    val biologyClass11Chapters = listOf(
        "The Living World",
        "Biological Classification",
        "Plant Kingdom",
        "Animal Kingdom",
        "Morphology of Flowering Plants",
        "Anatomy of Flowering Plants",
        "Structural Organisation in Animals",
        "Cell: The Unit of Life",
        "Biomolecules",
        "Cell Cycle and Cell Division",
        "Transport in Plants",
        "Mineral Nutrition",
        "Photosynthesis in Higher Plants",
        "Respiration in Plants",
        "Plant Growth and Development",
        "Digestion and Absorption",
        "Breathing and Exchange of Gases",
        "Body Fluids and Circulation",
        "Excretory Products and their Elimination",
        "Locomotion and Movement",
        "Neural Control and Coordination",
        "Chemical Coordination and Integration"
    )

    val biologyClass12Chapters = listOf(
        "Reproduction in Organisms",
        "Sexual Reproduction in Flowering Plants",
        "Human Reproduction",
        "Reproductive Health",
        "Principles of Inheritance and Variation",
        "Molecular Basis of Inheritance",
        "Evolution",
        "Human Health and Disease",
        "Strategies for Enhancement in Food Production",
        "Microbes in Human Welfare",
        "Biotechnology: Principles and Processes",
        "Biotechnology and its Applications",
        "Organisms and Populations",
        "Ecosystem",
        "Biodiversity and Conservation",
        "Environmental Issues"
    )

    data class SubjectData(val name: String, val chapters: List<String>, val color: String)

    fun getSubjectsForClass(classLevel: Int): List<SubjectData> {
        return if (classLevel == 11) {
            listOf(
                SubjectData("Physics", physicsChapters, "#FF6B35"),
                SubjectData("Chemistry", chemistryChapters, "#00D9FF"),
                SubjectData("Biology", biologyClass11Chapters, "#00D084")
            )
        } else {
            listOf(
                SubjectData("Physics", physicsChapters, "#FF6B35"),
                SubjectData("Chemistry", chemistryChapters, "#00D9FF"),
                SubjectData("Biology", biologyClass12Chapters, "#00D084")
            )
        }
    }
}
