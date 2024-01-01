package com.example.myapplication

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseModel = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciseModel.add(jumpingJacks)

        val plank = ExerciseModel(
            2,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseModel.add(plank)

        val pushUp = ExerciseModel(
            3,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciseModel.add(pushUp)

        val pushUpRotation = ExerciseModel(
            4,
            "push up and rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        exerciseModel.add(pushUpRotation)

        val sidePlank = ExerciseModel(
            5,
            "side plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        exerciseModel.add(sidePlank)

        val squat = ExerciseModel(
            6,
            "squat",
            R.drawable.ic_squat,
            false,
            false
        )
        exerciseModel.add(squat)

        val stepUp = ExerciseModel(
            7,
            "Stepup",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciseModel.add(stepUp)

        val triceps = ExerciseModel(
            8,
            "triceps",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciseModel.add(triceps)

        val wall = ExerciseModel(
            9,
            "wall",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseModel.add(wall)

        return exerciseModel
    }

}
