@org.hibernate.annotations.GenericGenerator(
        name = Constants.ID_GENERATOR,
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "CH10_IDENTIFIERS"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "10000"
                )
        }
)
package org.example.persistencedemo.part1;