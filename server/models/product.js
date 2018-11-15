'use strict';
module.exports = (sequelize, DataTypes) => {
	const Product = sequelize.define('Product', {
		name: {
			type: DataTypes.STRING,
			allowNull: false
		},
		price: {
			type: DataTypes.DOUBLE,
			allowNull: false
		}
	}, {});
	return Product;
};
