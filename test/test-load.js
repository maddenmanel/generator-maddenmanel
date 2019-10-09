/*global describe, beforeEach, it*/
'use strict';

var assert  = require('assert');

describe('spring generator', function () {
  it('can be imported without blowing up', function () {
    var app = require('../server');
    assert(app !== undefined);
  });
});
