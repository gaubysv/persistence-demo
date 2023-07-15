@org.hibernate.annotations.GenericGenerator(
        name = Constants.ID_GENERATOR,
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "identifiers"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "100000"
                )
        }
)
package org.example.persistencedemo.onetomany.embeddable;

import org.example.persistencedemo.Constants;