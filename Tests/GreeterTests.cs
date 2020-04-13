using Application;
using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace Tests
{
	public class GreeterTests
	{
		[Fact]
		public void SayHello_Test()
		{
			var sut = new Greeter();
			var actual = sut.SayHello();
			Assert.Equal("Hello World!", actual);
		}

		[Fact]
		public void SayHelloName_Test()
		{
			var sut = new Greeter();
			var actual = sut.SayHelloName("John");
			Assert.Equal("Hello John!", actual);
		}

		[Fact]
		public void Fail_Test()
		{
			Assert.True(false);
		}
	}
}