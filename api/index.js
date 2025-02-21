'use strict';

const Generator = require('yeoman-generator');  // Correct import
const chalk = require('chalk');
const mkdirp = require('mkdirp'); // Used to create directories

// Define the Spring generator class
module.exports = class SpringGenerator extends Generator {
  constructor(args, options) {
    super(args, options);  // Call the parent constructor
  }

  // Asynchronous method for prompting the user
  async askFor() {
    // console.log(chalk.green(`
    //   .............DD88888888888888888,............
    //   ...........:888888888888888888888,...........
    //   ..........+88888888888888888888888+..........
    //   .........,8888888888888888888888888..........
    //   .........888888888888...888888888888.........
    //   .......,88888887..D88...88Z..88888888,.......
    //   .......8888888,...888...88D...=8888888.......
    //   ......D888888,..$8888...88887...8888888......
    //   .....Z888888$..I88888...88888:..88888888,....
    //   ....D8888888...888888...88888D..,88888888....
    //   ....88888888,..888888..,888888...88888888....
    //   ....88888888,..8888888$888888D..,88888888....
    //   ....88888888I..Z8888888888888+..888888888....
    //   .....Z8888888...O888888888888..,88888888.....
    //   ......88888888...,88888888D...,88888888......
    //   .......88888888=.....?I+.....I88888888.......
    //   .......,88888888D7.........ZD88888888,.......
    //   .........888888888888888888888888888.........
    //   .........,8888888888888888888888888..........
    //   ..........+88888888888888888888888+..........
    //   ...........,888888888888888888888:...........
    //   .............DD888888888888888DD.............
    // `));

    // console.log(chalk.yellow('\nWelcome to the Spring Boot Generator\n\nLets get started!\n\n'));

    // Define prompts
    const prompts = [
      {
        type: 'input',
        name: 'packageName',
        message: '请输入包名:',
        default: 'com.maddenmanel'
      },
      {
        type: 'input',
        name: 'systemName',
        message: '请输入应用名称:',
        default: 'app'
      }
    ];

    // Prompt the user and capture their answers
    const props = await this.prompt(prompts);

    // Store user input
    this.packageName = props.packageName;
    this.systemName = props.systemName;
    this.baseName = this.systemName.replace(/\-/g, '.').replace(/\_/g, '.');  // Convert system name to base name
  }

  // This method is used to generate files based on user input
  app() {
    // Define directory paths based on the package name and system name
    const packageFolder = this.packageName.replace(/\./g, '/') + '/' + this.baseName.replace(/\./g, '/');
    const srcDir = `${this.systemName}/src/main/java/${packageFolder}`;
    const moDir = `${this.systemName}/src/main/java/${packageFolder}/dto`;
    const facadeDir = `${this.systemName}/src/main/java/${packageFolder}/facade`;

    // Create the necessary directories
    mkdirp.sync(srcDir);
    mkdirp.sync(moDir);
    mkdirp.sync(facadeDir);

    // Copy template files to the specified locations
    this.copy('_gitignore', `${this.systemName}/.gitignore`);
    this.template('pom.xml', `${this.systemName}/pom.xml`);
    this.template('Request.java', `${srcDir}/Request.java`);
    this.template('Response.java', `${srcDir}/Response.java`);
    this.template('UserDTO.java', `${moDir}/UserDTO.java`);
    this.template('UserServiceProvider.java', `${facadeDir}/UserServiceProvider.java`);

    // Store config values
    this.config.set('packageName', this.packageName);
    this.config.set('packageFolder', packageFolder);
  }

  // This method is a placeholder for future tasks
  projectfiles() {
    // No actions needed for now
  }
};
