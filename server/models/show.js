'use strict';
module.exports = (sequelize, DataTypes) => {
    var Show = sequelize.define('Show', {
        name: {
            type: DataTypes.STRING
        },
        description: {
            type: DataTypes.STRING
        },
        date: {
            type: DataTypes.DATE
        },
        price: {
            type: DataTypes.DOUBLE
        },
    }, {});
    return Show;
};
