'use strict';
module.exports = (sequelize, DataTypes) => {
	const Show = sequelize.define('Show', {
		name: {
			type: DataTypes.STRING,
			allowNull: false
		},
		description: {
			type: DataTypes.STRING
		},
		url: {
			type: DataTypes.STRING
		},
		date: {
			type: DataTypes.DATE,
			allowNull: false
		},
		price: {
			type: DataTypes.DOUBLE
		}
	}, {});
	return Show;
};
